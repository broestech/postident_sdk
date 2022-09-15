package com.broeskamp.postident

import java.util.*


val CONTENT_TYPE_HEADER_NAME: String = "Content-Type"
val CONTENT_TYPE_HEADER_VALUE: String = "application/json"

val AUTHORIZATION_HEADER_NAME: String = "Authorization"

val BASE_URL: String = "https://postident.deutschepost.de/api/"
val SIGNING_PATH: String = "scr-signing/v2/"


class PostIdentConfiguration(private var username: String, private var password: String) {

    fun getAuthValue(): String {
        val authData = Base64.getEncoder().encodeToString("%s:%s".format(username, password).toByteArray())
        return "Basic %s".format(authData);
    }
}