package com.broeskamp.postident

import java.net.http.HttpClient
import java.util.*

data class PostIdentConfiguration(
    private val username: String,
    private val password: String,
    private val clientId: String,
    private val baseUrl: String,
    val httpClient: HttpClient = HttpClient.newHttpClient()
) {

    val signingUri = "$baseUrl/scr-signing/v2/$clientId/signingcases"
    fun getSigningResultUri(caseId: String) = "$signingUri/$caseId"

    val authHeaderValue: String = "Basic %s".format(
        Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
    )

}