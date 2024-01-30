package com.broeskamp.postident

import com.nimbusds.jose.crypto.RSADecrypter
import java.net.URI
import java.net.http.HttpClient
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private const val HMAC_SHA256 = "HmacSHA256"


data class PostIdentConfiguration @JvmOverloads constructor(
    val username: String,
    private val password: String,
    private val clientId: String,
    private val baseUrl: String,
    private val dataPassword: String,
    private val privateKey: String? = null,
    private val publicKey: String? = null,
    val sftpConfig: PostIdentSftpConfiguration? = null,
    val httpClient: HttpClient = HttpClient.newHttpClient(),
) {

    private val signingUri = "$baseUrl/scr-signing/v2/$clientId/signingcases"
    private val identUri = "$baseUrl/api/scr/v1/$clientId/cases"
    private val sha256mac = Mac.getInstance(HMAC_SHA256)
    val rsaDecrypter: RSADecrypter
    val publicKeyHeaderValue: String
    val publicKeyHash: String


    init {
        val shaMacKeySpec = SecretKeySpec(dataPassword.toByteArray(), HMAC_SHA256)
        sha256mac.init(shaMacKeySpec)

        val keypair = RsaKeypairUtil.generateKeypair(privateKey, publicKey)

        publicKeyHeaderValue = RsaKeypairUtil.getPublicKeyEncoded(keypair.public)
        publicKeyHash = Base64.getEncoder()
            .encodeToString(sha256mac.doFinal(Base64.getDecoder().decode(publicKeyHeaderValue)))
        rsaDecrypter = RSADecrypter(keypair.private)
    }


    fun getSigningUri(): URI = URI.create(signingUri)

    fun getIdentUri(): URI = URI.create(identUri)

    fun getSigningResultUri(caseId: String): URI =
        URI.create("$signingUri/$caseId?includeBinaryData=true")

    fun getIdentResultUri(caseId: String): URI =
        URI.create("$identUri/$caseId/full?includeBinaryData=true")


    val authHeaderValue: String = "Basic %s".format(
        Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
    )


}