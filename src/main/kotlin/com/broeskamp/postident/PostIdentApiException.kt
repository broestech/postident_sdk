package com.broeskamp.postident

import org.slf4j.LoggerFactory
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private val logger = LoggerFactory.getLogger(PostIdentApiException::class.java)

class PostIdentApiException(request: HttpRequest, response: HttpResponse<String>) :
    RuntimeException(
        "PostIdentApi %s request to %s returned error with status code %s".format(
            request.method(),
            request.uri().toString(),
            response.statusCode().toString(),
        )
    ) {

    init {
        logger.debug("Response Body %s".format(response.body()))
    }
}