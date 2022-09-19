package com.broeskamp.postident


import com.broeskamp.postident.dto.request.SigningCaseRequest
import com.broeskamp.postident.dto.response.SigningCaseResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers

class PostIdentApi(private val config: PostIdentConfiguration) {

    private val mapper: ObjectMapper = jacksonObjectMapper()
    private val httpClient: HttpClient = HttpClient.newHttpClient()

    fun createSigningCase(signingCaseRequest: SigningCaseRequest): SigningCaseResponse {
        val request = getHttpRequestBuilder()
            .uri(URI.create(config.signingUri))
            .POST(BodyPublishers.ofString(mapper.writeValueAsString(signingCaseRequest)))
            .build()
        return executeRequest(request, SigningCaseResponse::class.java)
    }

    private fun <T> executeRequest(request: HttpRequest, responseClass: Class<T>): T {
        try {
            val response = httpClient.send(request, BodyHandlers.ofString())
            return mapper.readValue(response.body(), responseClass)
        } catch (exception: InterruptedException) {
            Thread.currentThread().interrupt()
            throw ThreadInterruptedException(exception)
        }
    }

    private fun getHttpRequestBuilder(): HttpRequest.Builder {
        return HttpRequest.newBuilder()
            .header(config.contentTypeHeaderName, config.contentTypeHeaderValue)
            .header(config.authorizationHeaderName, config.getAuthValue())
    }
}