package com.broeskamp.postident

import com.nimbusds.jose.crypto.RSADecrypter
import java.net.URI
import java.net.http.HttpClient
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
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
    private val privateKey: String,
    private val publicKey: String,
    val sftpConfig: PostIdentSftpConfiguration?,
    val httpClient: HttpClient = HttpClient.newHttpClient(),
) {

    private val signingUri = "$baseUrl/scr-signing/v2/$clientId/signingcases"
    private val identUri = URI.create("$baseUrl/scr/v1")
    private val sha256mac = Mac.getInstance(HMAC_SHA256)
    val rsaDecrypter: RSADecrypter

    init {
        val keySpec = SecretKeySpec(dataPassword.toByteArray(), HMAC_SHA256)
        sha256mac.init(keySpec)
        val formattedPrivateKey = privateKey.replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("\n", "")
            .replace("\r", "")
            .replace("-----END PRIVATE KEY-----", "")
        val decodedPrivateKey = Base64.getDecoder().decode(formattedPrivateKey)
        val privateRsaKey: RSAPrivateKey = KeyFactory.getInstance("RSA")
            .generatePrivate(PKCS8EncodedKeySpec(decodedPrivateKey)) as RSAPrivateKey
        rsaDecrypter = RSADecrypter(privateRsaKey)
    }

    fun getSigningUri(): URI = URI.create(signingUri)
    fun getSigningResultUri(caseId: String): URI =
        URI.create("$signingUri/$caseId?includeBinaryData=true")

    fun getIdentResultUri(caseId: String): URI =
        URI.create("$identUri/$clientId/cases/$caseId/full?includeBinaryData=true")


    val authHeaderValue: String = "Basic %s".format(
        Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
    )

    val publicKeyHeaderValue: String =
        publicKey.replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("\n", "")
            .replace("\r", "")
            .replace("-----END PUBLIC KEY-----", "")

    val publicKeyHash: String =
        Base64.getEncoder()
            .encodeToString(sha256mac.doFinal(Base64.getDecoder().decode(publicKeyHeaderValue)))
}