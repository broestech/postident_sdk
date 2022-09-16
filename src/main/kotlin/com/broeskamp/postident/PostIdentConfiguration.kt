package com.broeskamp.postident

import java.util.*


class PostIdentConfiguration(
    private var username: String,
    private var password: String,
    private var clientId: String,
) {

    val CONTENT_TYPE_HEADER_NAME: String = "Content-Type"
    val CONTENT_TYPE_HEADER_VALUE: String = "application/json"

    val AUTHORIZATION_HEADER_NAME: String = "Authorization"

    private val BASE_URL: String = "https://postident.deutschepost.de/api/"
    private val SIGNING_PATH: String = "scr-signing/v2/"

    fun getAuthValue(): String {
        val authData =
            Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
        return "Basic %s".format(authData)
    }

    fun getSigningUri(): String {
        return "$BASE_URL$SIGNING_PATH$clientId/signingcases"
    }
}