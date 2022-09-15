package com.broeskamp.postident


import com.broeskamp.postident.dto.request.SigningCaseRequest
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.http.HttpClient

class PostIdent(private val config: PostIdentConfiguration) {

    private val mapper: ObjectMapper = ObjectMapper()
    private val httpClient: HttpClient = HttpClient.newHttpClient()

    fun createSigningCase(signingCaseRequest: SigningCaseRequest):
}