package com.broeskamp.postident.dto.request.signing

import com.broeskamp.postident.dto.MultiPlatformUrl
import com.broeskamp.postident.dto.PreferredLanguage
import com.broeskamp.postident.dto.request.ProcessData
import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder
import java.time.LocalDate

@Builder
data class SigningCaseProcessData(
    override val targetCountry: String?,
    override val preferredLanguage: PreferredLanguage?,
    override val webHookUrl: String?,
    override val referenceId: String?,

    /**
     * The signing case URL will expire and the signing case will be declined
     * at the end of this day in case the documents aren't signed yet. ISO 8601
     * format. Maximum 30 days
     *
     * Max length: 10
     *
     * @sample "2016-01-28"
     */
    val validUntil: LocalDate,

    /**
     * Will be shown to the user during the signing process. The phone number
     * can start with "+" and contain 0-9 and blanks
     *
     * Max length: 20
     *
     * @sample "0228 12 34 56 78"
     */
    val phoneNumberClientCustomerService: String?,

    /** Redirect or callback URL for user if signing case is declined */
    val callbackUrlSigningDeclined: MultiPlatformUrl?,

    /**
     * Mark "true" if user has to confirm the reading of the documents before
     * signing the documents
     */
    val userMustConfirmHavingReadDocuments: Boolean?,

    /**
     * A note regarding the client customer service. Will be shown below the
     * client customer service phone number in the frontend.
     *
     * Max length: 100
     */
    val noteClientCustomerService: String?,

    /**
     * Key for the text that will be displayed on the button to sign the
     * documents. Possible values: 1-5
     * 1. Rechtsgültig unterschreiben
     * 2. Zahlungspflichtigen Vertrag unterschreiben
     * 3. Digital unterschreiben
     * 4. Unterschreiben
     * 5. Kostenpflichtig kaufen
     */
    val signingButtonLabel: SigningButtonLabel?,

    ) : ProcessData() {
    enum class SigningButtonLabel {

        @JsonProperty("1")
        LEGAL_SIGNATURE,

        @JsonProperty("2")
        PAYMENT_SIGNATURE,

        @JsonProperty("3")
        DIGITAL_SIGNATURE,

        @JsonProperty("4")
        SIGN,

        @JsonProperty("5")
        PURCHASE
    }
}