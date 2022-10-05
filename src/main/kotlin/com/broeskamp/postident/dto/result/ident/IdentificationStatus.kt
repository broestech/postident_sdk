package com.broeskamp.postident.dto.result.ident


import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder
import java.time.Instant

@Builder
data class IdentificationStatus(

    val status: Status,

    /**
     * Containing "code" and "description" to specify the substatus.
     */
    val subStatus: ResultStatus?,

    /**
     * Additional information from agent regarding fraud suspicion
     */
    val subStatusReason: ResultStatus?,
    val identificationTime: Instant?,
    val created: Instant,
    val modified: Instant,
) {
    enum class Status {

        @JsonProperty("started")
        STARTED,

        @JsonProperty("coupon requested")
        COUPON_REQUESTED,

        @JsonProperty("incomplete")
        INCOMPLETE,

        @JsonProperty("review pending")
        REVIEW_PENDING,

        @JsonProperty("declined")
        DECLINED,

        @JsonProperty("success")
        SUCCESS
    }
}
