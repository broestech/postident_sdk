package com.broeskamp.postident.dto.request

import com.thinkinglogic.builder.annotation.Builder
import java.time.LocalDate

@Builder
data class ProcessData(
    /**
     *
     * The display name of the case to be shown to the user in the signing process.
     *
     * Max length: 150
     *
     * @sample "Kreditantrag"
     */
    val caseName: String,

    /**
     * Country from which the identification is requested. ISO 3166-1 ALPHA-3.  If not provided, E-Signing application will set the default value DEU.
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    val targetCountry: String?,

    val preferredLanguage: PreferredLanguage?,

    /**
     * URL for push notifications back to your application. The URL will be called via POST request. Parameter is application/json with the body {"caseId"="##caseId##"}
     *
     * Max length: 500
     *
     * @sample "https://democompany.com/api/piwebhook"
     */
    val webHookUrl: String?,

    /**
     * The reference id of the client. If provided, this must be unique in the context of the client id
     *
     * Max length: 14
     *
     * @sample "K2345ASDF"
     */
    val referenceId: String?,

    /**
     * The signing case URL will expire and the signing case will be declined at the end of this day in case the documents aren't signed yet. ISO 8601 format. Maximum 30 days
     *
     * Max length: 10
     *
     * @sample "2016-01-28"
     */
    val validUntil: LocalDate,

    /**
     * Will be shown to the user during the signing process. The phone number can start with "+" and contain 0-9 and blanks
     *
     * Max length: 20
     *
     * @sample "0228 12 34 56 78"
     */
    val phoneNumberClientCustomerService: String?,

    /**
     * Redirect or callback URL for user if signing case is declined
     */
    val callbackUrlSigningDeclined: MultiPlatformUrl?,

    /**
     * Mark "true" if user has to confirm the reading of the documents before signing the documents
     */
    val userMustConfirmHavingReadDocuments: Boolean?,

    /**
     * A note regarding the client customer service. Will be shown below the client customer service phone number in the frontend.
     *
     * Max length: 100
     */
    val noteClientCustomerService: String?,

    /**
     * Key for the text that will be displayed on the button to sign the documents. Possible values: 1-5
     *
     * 1. Rechtsgültig unterschreiben
     * 2. Zahlungspflichtigen Vertrag unterschreiben
     * 3. Digital unterschreiben
     * 4. Unterschreiben
     * 5. Kostenpflichtig kaufen
     */
    val signingButtonLabel: SigningButtonLabel?,

    )