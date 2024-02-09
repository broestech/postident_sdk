package com.broeskamp.postident.dto.request

/**
 * Depending on the platform, the corresponding URL from here will be
 * used. On mobile platforms, the web URL will be used if the iOSUrl resp.
 * androidUrl is not set.
 */
data class MultiplatformUrl(
    val webUrl: String? = null,
    val iOSUrl: String? = null,
    val androidUrl: String? = null
)