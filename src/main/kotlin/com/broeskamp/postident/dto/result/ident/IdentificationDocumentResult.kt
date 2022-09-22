package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class IdentificationDocumentResult(
    val records: List<RecordResult>?,

    /**
     * Type of document
     */
    val type: ResultValue,

    /**
     * Number of document
     */
    val number: ResultValue?,

    /**
     * All given names as printed on the identification document
     */
    val firstName: ResultValue,

    /**
     * Exact last name as printed on identification document; may include title like "Dr."
     */
    val lastName: ResultValue,

    /**
     * Only if differing from last name
     *
     * Do not include prefixes like „geb.“ or „Geborene“
     */
    val birthName: ResultValue?,

    /**
     * Birth date of customer as ISO 8601 format
     */
    val birthDate: ResultValue,

    /**
     * Place of birth.
     */
    val birthPlace: ResultValue?,

    /**
     * Nationality according to ISO 3166 alpha 3 plus
     */
    val nationality: ResultValue?,

    /**
     * Contains the postal address which is printed on the document. If the document has no address, this field will be empty.
     */
    val address: AddressResult,

    /**
     * Date of issuance as ISO 8601 format.
     */
    val dateIssued: ResultValue?,

    /**
     * Date of expiry as ISO 8601 format.
     */
    val dateOfExpiry: ResultValue,

    /**
     * Authority issuing the document.
     */
    val authority: ResultValue?,

    /**
     * Place of authority. Field in German passport only.
     */
    val placeOfIssue: ResultValue?,

    /**
     * Country code as ISO-3166 ALPHA-3 plus RKS for Kosovo.
     */
    val countryOfDocument: ResultValue,
)
