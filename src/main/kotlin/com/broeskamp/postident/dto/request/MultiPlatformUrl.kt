package com.broeskamp.postident.dto.request

data class MultiPlatformUrl(
    /**
     * URL for web browser
     *
     * Max length: 200
     *
     * @sample "https://musterbank.eu/pi-signing-callback/success.html"
     */
    val webUrl: String
)