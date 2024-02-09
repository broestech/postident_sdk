package com.broeskamp.postident.dto.response.ident

import com.thinkinglogic.builder.annotation.Builder
import java.time.Instant


@Builder
data class IdentCaseResponse(
    /**
     * Unique id of the POSTIDENT system for a case, while case is in POSTIDENT
     * system. May be reused after case is deleted in POSTIDENT system.
     *
     * @sample "123456789ABC"
     */
    val caseId: String,
    /**
     * Date and time until which caseId and start/restart URLs will expire;
     * after that the case cannot be started or restarted by the user.
     *
     * ISO 8601 format, accuracy in seconds, the offset to Zulu time ±hh:mm at
     * the end.
     *
     * @sample "2016-04-28T23:59:59+01:00"
     */
    val validUntil: Instant,
    /** Start/restart information for web */
    val webStart: IdentWebStart,
    /** Start/restart information for iOS */
    val iosStart: IdentMobileStart,
    /** Start/restart information for Android */
    val androidStart: IdentMobileStart

)
