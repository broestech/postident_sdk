package com.broeskamp.postident

import java.net.URI
import java.net.http.HttpClient
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val HMAC_SHA256 = "HmacSHA256"

data class PostIdentConfiguration @JvmOverloads constructor(
    val username: String,
    private val password: String,
    private val clientId: String,
    private val baseUrl: String,
    private val dataPassword: String,
    private val privateKey: String,
    private val publicKey: String,
    val sftpConfig: PostIdentSftpConfiguration?,
    val httpClient: HttpClient = HttpClient.newHttpClient(),
) {

    private val signingUri = "$baseUrl/scr-signing/v2/$clientId/signingcases"
    private val identUri = URI.create("$baseUrl/scr/v1")
    private val sha256mac = Mac.getInstance(HMAC_SHA256)

    init {
        val keySpec = SecretKeySpec(dataPassword.toByteArray(), HMAC_SHA256)
        sha256mac.init(keySpec)
    }

    fun getSigningUri(): URI = URI.create(signingUri)
    fun getSigningResultUri(caseId: String): URI =
        URI.create("$signingUri/$caseId?includeBinaryData=true")

    fun getIdentResultUri(caseId: String): URI =
        URI.create("$identUri/$clientId/cases/$caseId/full?includeBinaryData=true")


    val authHeaderValue: String = "Basic %s".format(
        Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
    )

    val publicKeyHash: String =
        Base64.getUrlEncoder().encodeToString(sha256mac.doFinal(publicKey.toByteArray()))

}