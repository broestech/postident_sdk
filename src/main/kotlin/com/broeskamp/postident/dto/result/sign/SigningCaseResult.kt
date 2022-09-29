package com.broeskamp.postident.dto.result.sign

import com.broeskamp.postident.dto.CustomData
import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder
import java.time.Instant
import java.time.LocalDate

/**
 * DTO received when requesting result of signing case
 */
@Builder
data class SigningCaseResult(

    /**
     * Unique Id for your signing case
     *
     * @sample "M3FB00URX4A3"
     */
    val caseId: String,

    /**
     * @sample "signed"
     */
    val caseStatus: CaseStatus,

    /**
     * @sample "validity period expired"
     */
    val caseSubStatus: CaseSubStatus,

    /**
     * Creation time of the signing case
     *
     * Max length: 26
     *
     * @sample "2017-01-28T23:59:59+01:00"
     */
    val created: Instant,

    val signedDocuments: List<SignedDocument>?,

    val accountingData: AccountingData?,

    val customData: CustomData,

    /**
     * Your reference of the signing case
     *
     * Max length: 30
     *
     * @sample "2345asfd12"
     */
    val referenceId: String?,

    val signers: List<SignerResult>,

    /**
     *  The signing case URL will expire and the signing case will be cancelled at the end of this day. ISO 8601 format
     *
     *  Max length: 10
     *
     *  @sample "2017-01-28"
     */
    val validUntil: LocalDate?,

    /**
     * The signed documents will be available for download by the user and via SCR-Signing until the end of this day. Afterwards the signing case will be transitioned to the state closed. ISO 8601 format
     *
     * Max length: 10
     *
     * @sample "2017-01-28"
     */
    val storeUntil: LocalDate?,


    ){
    enum class CaseStatus {
        @JsonProperty("new")
        NEW,

        @JsonProperty("signing process")
        SIGNING_PROCESS,

        @JsonProperty("signed")
        SIGNED,

        @JsonProperty("closed")
        CLOSED,

        @JsonProperty("declined")
        DECLINED
    }

    /**
     * - for caseStatus declined:
     *      - validity period expired
     *      - signer was declined
     * - for caseStatus closed:
     *      - archived
     */
    enum class CaseSubStatus {
        @JsonProperty("validity period expired")
        VALIDITY_PERIOD_EXPIRED,

        @JsonProperty("signer was declined")
        SIGNER_WAS_DECLINED,

        @JsonProperty("archived")
        ARCHIVED
    }
}
