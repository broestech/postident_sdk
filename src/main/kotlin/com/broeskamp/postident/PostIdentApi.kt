package com.broeskamp.postident


import com.broeskamp.postident.dto.request.ident.IdentCaseRequest
import com.broeskamp.postident.dto.request.signing.SigningCaseRequest
import com.broeskamp.postident.dto.response.ident.IdentCaseResponse
import com.broeskamp.postident.dto.response.signing.SigningCaseResponse
import com.broeskamp.postident.dto.result.ident.IdentCaseResult
import com.broeskamp.postident.dto.result.sign.SigningCaseResult
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nimbusds.jose.JWEObject
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.transport.verification.PromiscuousVerifier
import net.schmizz.sshj.userauth.password.PasswordFinder
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers
import java.util.concurrent.CompletableFuture

private const val CONTENT_TYPE_HEADER_NAME: String = "Content-Type"
private const val CONTENT_TYPE_HEADER_VALUE: String = "application/json"
private const val AUTHORIZATION_HEADER_NAME: String = "Authorization"

private val mapper: ObjectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

private val logger = LoggerFactory.getLogger(PostIdentApi::class.java)

class PostIdentApi(
    private val config: PostIdentConfiguration,
) {

    fun createSigningCase(signingCaseRequest: SigningCaseRequest): CompletableFuture<SigningCaseResponse> {
        val request = getHttpRequestBuilder()
            .uri(config.getSigningUri())
            .POST(BodyPublishers.ofString(mapper.writeValueAsString(signingCaseRequest)))
            .build()
        return executeRequest(request, SigningCaseResponse::class.java)
    }

    fun createIdentCase(identCaseRequest: IdentCaseRequest): CompletableFuture<IdentCaseResponse> {
        val request = getHttpRequestBuilder()
            .uri(config.getIdentUri())
            .POST(BodyPublishers.ofString(mapper.writeValueAsString(identCaseRequest)))
            .build()
        return executeRequest(request, IdentCaseResponse::class.java)
    }

    fun retrieveSigningCaseResult(caseId: String): CompletableFuture<SigningCaseResult> {
        val request = getEncryptedHttpRequestBuilder()
            .uri(config.getSigningResultUri(caseId))
            .GET()
            .build()
        return executeRequest(request, SigningCaseResult::class.java, true)
    }

    fun retrieveIdentCaseResult(identCaseId: String): CompletableFuture<IdentCaseResult> {
        val request = getEncryptedHttpRequestBuilder()
            .uri(config.getIdentResultUri(identCaseId))
            .GET()
            .build()
        return executeRequest(request, IdentCaseResult::class.java, true)
    }

    fun retrieveVideoIdentZip(caseId: String): PostIdentFile {
        if (config.sftpConfig == null) {
            throw ConfigurationMissingException("Cannot retrieve Video without a SFTP Configuration")
        }
        val sshClient = SSHClient()
        sshClient.addHostKeyVerifier(PromiscuousVerifier())
        sshClient.connect(config.sftpConfig.host)
        val passwordFinder: PasswordFinder? = if (config.sftpConfig.keyPassword != null) {
            PostidentPasswordFinder(config.sftpConfig.keyPassword)
        } else {
            null
        }
        val keyProvider =
            sshClient.loadKeys(
                config.sftpConfig.privateKey,
                config.sftpConfig.publicKey,
                passwordFinder
            )

        sshClient.authPublickey(config.username, keyProvider)
        val sftpClient = sshClient.newSFTPClient()
        val filePath =
            "${config.sftpConfig.path}${
                config.sftpConfig.getVideoZipFilename(
                    config.username,
                    caseId
                )
            }"
        logger.info("Opening file: $filePath to retrieve videoRecording from PostIdent with caseId: $caseId")
        val remoteFile = sftpClient.open(filePath)
        val inputStream = remoteFile.RemoteFileInputStream()

        return PostIdentFile(
            config.sftpConfig.getVideoZipFilename(config.username, caseId),
            inputStream
        )
    }

    private fun <T> executeRequest(
        request: HttpRequest,
        responseClass: Class<T>,
        encrypted: Boolean = false
    ): CompletableFuture<T> {
        val futureResponse = config.httpClient.sendAsync(request, BodyHandlers.ofString())
        return futureResponse.thenApply { response ->
            if (response.statusCode() == HttpURLConnection.HTTP_CREATED || response.statusCode() == HttpURLConnection.HTTP_OK) {
                val responseBody: String = if (encrypted) {
                    val jweObject = JWEObject.parse(response.body())
                    jweObject.decrypt(config.rsaDecrypter)
                    jweObject.payload.toString()
                } else {
                    response.body()
                }
                return@thenApply mapper.readValue(responseBody, responseClass)
            } else {
                throw PostIdentApiException(request, response)
            }
        }
    }

    private fun getHttpRequestBuilder(): HttpRequest.Builder =
        HttpRequest.newBuilder()
            .header(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)
            .header(AUTHORIZATION_HEADER_NAME, config.authHeaderValue)


    private fun getEncryptedHttpRequestBuilder(): HttpRequest.Builder =
        getHttpRequestBuilder()
            .header("x-scr-key", config.publicKeyHeaderValue)
            .header("x-scr-keyhash", config.publicKeyHash)
            .header("x-scr-alg", "RSA-OAEP-256")
}