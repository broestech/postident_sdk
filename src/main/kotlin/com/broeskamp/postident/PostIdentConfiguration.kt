package com.broeskamp.postident

import java.net.URI
import java.net.http.HttpClient
import java.util.*


data class PostIdentConfiguration @JvmOverloads constructor(
    val username: String,
    private val password: String,
    private val clientId: String,
    private val baseUrl: String,
    val sftpHost: String?,
    val publicKey: String?,
    val privateKey: String?,
    private val billingNumber: String?,
    val httpClient: HttpClient = HttpClient.newHttpClient()
) {

    private val signingUri = "$baseUrl/scr-signing/v2/$clientId/signingcases"
    private val identUri = URI.create("$baseUrl/scr/v1")

    fun getSigningUri(): URI = URI.create(signingUri)
    fun getSigningResultUri(caseId: String): URI =
        URI.create("$signingUri/$caseId?includeBinaryData=true")

    fun getIdentResultUri(caseId: String): URI =
        URI.create("$identUri/$clientId/cases/$caseId/full?includeBinaryData=true")

    fun getVideoZipFilename(caseId: String) =
        "VIDEOCHATRECORDING_%s_%s_%s.zip".format(username, billingNumber, caseId)

    val authHeaderValue: String = "Basic %s".format(
        Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
    )

}