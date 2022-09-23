package com.broeskamp.postident.dto.result.ident

import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder
import java.time.Instant

@Builder
data class CaseStatus(
    val status: Status,

    /**
     * Flag for archived case
     *
     * @sample false
     */
    val archived: Boolean,

    /**
     * Date and time until which case ID will expire; after that, the case cannot be started or restarted by the user
     *
     * ISO 8601 format, accuracy in seconds, the offset to Zulu time ±hh:mm at the end
     *
     * @sample 2016-04-28T23:59:59+01:00
     */
    val validUntil: Instant,

    /**
     * ISO 8601 format, accuracy in seconds, the offset to Zulu time ±hh:mm at the end
     *
     * @sample 2016-04-28T23:59:59+01:00
     */
    val created: Instant,

    /**
     * ISO 8601 format, accuracy in seconds, the offset to Zulu time ±hh:mm at the end
     *
     * @sample 2016-04-28T23:59:59+01:00
     */
    val modified: Instant
) {
    enum class Status {
        @JsonProperty("new")
        NEW,

        @JsonProperty("in progress")
        IN_PROGRESS,

        @JsonProperty("closed")
        CLOSED
    }
}