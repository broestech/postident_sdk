package com.broeskamp.postident.dto.result.sign

import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder
import java.time.Instant

@Builder
data class SignerResult(

    /**
     * Number of the signer
     *
     * @sample "1"
     */
    val signerNumber: String,

    /**
     * ID of the corresponding video ident case, available beginning with status identification. Can be used to query ident status and detailed ident data from the SCR-Ident API
     *
     * Max length: 12
     *
     * @sample "A3KF00URX2A9"
     */
    val identCaseId: String?,

    /**
     * Creation time of the signer
     *
     * Max length: 26
     *
     * @sample "2017-01-28T23:59:59+01:00"
     */
    val created: Instant,

    /**
     * Last modification time of the signer
     *
     * Max length: 26
     *
     * @sample "2017-01-28T23:59:59+01:00"
     */
    val modified: Instant?,

    /**
     * Date and time of successful identification. ISO 8601 format, accuracy in seconds, the offset to Zulu time ±hh:mm at the end
     *
     * Max length: 26
     *
     * @sample "2017-01-28T23:59:59+01:00"
     */
    val identificationTime: Instant?,

    /**
     * Method used for identifying the signer
     *
     * Max length: 35
     *
     * @sample "video"
     */
    val identificationMethod: String?,

    /**
     * Date and time of successful signing of the documents. ISO 8601 format, accuracy in seconds, the offset to Zulu time ±hh:mm at the end
     *
     * Max length: 26
     *
     * @sample "2017-01-28T23:59:59+01:00"
     */
    val signingTime: Instant?,

    /**
     * Time when signer accepted E-Signing terms and conditions
     *
     * Max length: 26
     *
     * @sample "2017-01-28T23:59:59+01:00"
     */
    val termsAndConditionsAcceptedTime: Instant?,

    /**
     * Only relevant if E-Mail communication by E-Signing System is deactivated:
     *
     * If true:
     *
     * - if identification **succeeded** you have to notify the user to continue the signature process (signer state equals "signing process")
     * - if identification **failed** you have to notify the user that signature process is declined (signer state equals "declined" and subStatus equals "ident declined").
     */
    val notifyUserAboutSigningState: Boolean?,

    /**
     * Time when signer confirms reading all documents. Will be provided if this feature was specified in the signing case using SCR-Signing POST
     *
     * Max length: 26
     *
     * @sample "2017-01-28T23:59:59+01:00"
     */
    val userReadDocumentsTime: Instant?,

    /**
     * Status of the signing case
     *
     * Max length: 100
     */
    val signerStatus: SignerStatus,

    val signerSubStatus: SignerSubStatus?,

    val signerContactData: SignerContactData?,

    val identityData: IdentifyDataResult?,

    val identificationDocument: IdentificationDocument?,

    /**
     * Indexes of the documents signed by the given signer. The indexes are pointers in the documents list of the case. First document in the list has the position 1.
     */
    val signedDocuments: List<Number>,

    ){

    enum class SignerStatus {
        @JsonProperty("new")
        NEW,

        @JsonProperty("identification")
        IDENTIFICATION,

        @JsonProperty("signing process")
        SIGNING_PROCESS,

        @JsonProperty("signed")
        SIGNED,

        @JsonProperty("finished")
        FINISHED,

        @JsonProperty("declined")
        DECLINED
    }


    /**
     * Substatus describing the status on a more detailed level. Possible values:
     *
     * - signerStatus 'declined':
     *      - signing declined
     *      - mobil phone number not verified
     *      - ident declined
     *      - maximum number of started operations exceeded
     *      - maximum number of created certificates exceeded
     *      - maximum number of sent sms exceeded
     *      - other signer was declined
     * - signerStatus 'declined', 'signing process'
     *      - prolongued duration of identification postprocess
     */
    enum class SignerSubStatus {
        @JsonProperty("signing declined")
        SIGNING_DECLINED,

        @JsonProperty("mobil phone number not verified")
        MOBILE_PHONE_NUMBER_NOT_VERIFIED,

        @JsonProperty("ident declined")
        IDENT_DECLINED,

        @JsonProperty("maximum number of started operations exceeded")
        MAXIMUM_NUMBER_OF_STARTED_OPERATIONS_EXCEEDED,

        @JsonProperty("maximum number of created certificates exceeded")
        MAXIMUM_NUMBER_OF_CERTIFICATES_EXCEEDED,

        @JsonProperty("maximum number of sent sms exceeded")
        MAXIMUM_NUMBER_OF_SMS_EXCEEDED,

        @JsonProperty("other signer was declined")
        OTHER_SIGNER_WAS_DECLINED,

        @JsonProperty("prolongued duration of identification postprocess")
        PROLONGUED_DURATION_OF_IDENTIFICATION_POSTPROCESS
    }
}
