package com.broeskamp.postident.dto

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class MultiPlatformUrl(
    /**
     * URL for web browser
     *
     * Max length: 200
     *
     * @sample "https://musterbank.eu/pi-signing-callback/success.html"
     */
    val webUrl: String?
)