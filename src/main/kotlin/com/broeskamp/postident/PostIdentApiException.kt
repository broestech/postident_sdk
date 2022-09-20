package com.broeskamp.postident

import mu.KotlinLogging
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private val logger = KotlinLogging.logger {}

class PostIdentApiException(request: HttpRequest, response: HttpResponse<String>) :
    RuntimeException(
        "PostIdentApi returned error with status code %s and response body %s".format(
            response.statusCode().toString(),
            response.body()
        )
    ) {

    init {
        logger.debug {
            "PostIdentApi request method: %s request URI: %s".format(
                request.method(),
                request.uri().toString()
            )
        }
    }

}