package com.broeskamp.postident


import com.broeskamp.postident.dto.request.SigningCaseRequest
import com.broeskamp.postident.dto.response.SigningCaseResponse
import com.broeskamp.postident.dto.result.ident.IdentCaseResult
import com.broeskamp.postident.dto.result.sign.SigningCaseResult
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers
import java.util.concurrent.CompletableFuture

private const val CONTENT_TYPE_HEADER_NAME: String = "Content-Type"
private const val CONTENT_TYPE_HEADER_VALUE: String = "application/json"
private const val AUTHORIZATION_HEADER_NAME: String = "Authorization"


class PostIdentApi(
    private val config: PostIdentConfiguration,
    private val mapper: ObjectMapper = jacksonObjectMapper()
) {

    fun createSigningCase(signingCaseRequest: SigningCaseRequest): CompletableFuture<SigningCaseResponse> {
        val request = getHttpRequestBuilder()
            .uri(config.getSigningUri())
            .POST(BodyPublishers.ofString(mapper.writeValueAsString(signingCaseRequest)))
            .build()
        return executeRequest(request, SigningCaseResponse::class.java)
    }

    fun retrieveSigningCaseResult(caseId: String): CompletableFuture<SigningCaseResult>{
        val request = getHttpRequestBuilder()
            .uri(config.getSigningResultUri(caseId))
            .GET()
            .build()
        return executeRequest(request, SigningCaseResult::class.java)
    }

    fun retrieveIdentCaseResult(caseId: String): CompletableFuture<IdentCaseResult>{
        val request = getHttpRequestBuilder()
            .uri(config.getIdentResultUri(caseId))
            .GET()
            .build()
        return executeRequest(request, IdentCaseResult::class.java)
    }

    private fun <T> executeRequest(
        request: HttpRequest,
        responseClass: Class<T>
    ): CompletableFuture<T> {
        val futureResponse = config.httpClient.sendAsync(request, BodyHandlers.ofString())
        return futureResponse.thenApply { response ->
            if (response.statusCode() == 200) {
                return@thenApply mapper.readValue(response.body(), responseClass)
            } else {
                throw PostIdentApiException(request, response)
            }
        }
    }

    private fun getHttpRequestBuilder(): HttpRequest.Builder =
        HttpRequest.newBuilder()
            .header(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)
            .header(AUTHORIZATION_HEADER_NAME, config.authHeaderValue)

}