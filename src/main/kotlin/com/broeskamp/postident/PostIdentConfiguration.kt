package com.broeskamp.postident

import java.util.*


data class PostIdentConfiguration(
    private val username: String,
    private val password: String,
    private val clientId: String,
    private val baseUrl: String,
) {

    val contentTypeHeaderName: String = "Content-Type"
    val contentTypeHeaderValue: String = "application/json"

    val authorizationHeaderName: String = "Authorization"

    private val signingPath: String = "scr-signing/v2/"

    val signingUri = "$baseUrl$signingPath$clientId/signingcases"

    fun getAuthValue(): String {
        val authData =
            Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
        return "Basic %s".format(authData)
    }


}