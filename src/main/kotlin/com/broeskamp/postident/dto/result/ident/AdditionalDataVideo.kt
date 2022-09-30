package com.broeskamp.postident.dto.result.ident

import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder
import java.time.Instant

@Builder
data class AdditionalDataVideo(

    /**
     * Service center, where the identification took place
     *
     * @sample "1 DP CSC GmbH, Location Flensburg"
     */
    val identificationServiceCenter: String?,

    /**
     * Alias of Agent, who performed this identification
     */
    val identificationAgentAlias: String?,

    /**
     * List of reviews performed (only for class TKG)
     */
    val identReviews: String?,

    /**
     * Currently not filled, always empty
     */
    val auditTrailItems: List<String>?,

    /**
     * Time of the user's consent to the video recording
     */
    val videoRecordingAccepted: Instant?,

    /**
     * Channel where TAN has been sent to (only for class GWG). = ['sms', 'email']
     */
    val tanChannel: TanChannel?,

    val videoRecordingDeliveryShaft: Boolean?,

    ) {
    enum class TanChannel {

        @JsonProperty("sms")
        SMS,

        @JsonProperty("email")
        EMAIL
    }
}
