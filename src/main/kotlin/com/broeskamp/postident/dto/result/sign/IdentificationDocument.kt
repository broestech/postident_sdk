package com.broeskamp.postident.dto.result.sign

import com.thinkinglogic.builder.annotation.Builder
import java.time.LocalDate

@Builder
data class IdentificationDocument(
    val type: DocumentType,

    /**
     * Max length: 20
     */
    val number: String,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "2002-02-01"
     */
    val dateIssued: LocalDate?,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "2002-02-01"
     */
    val dateOfExpiry: LocalDate,

    /**
     * Max length: 100
     *
     * @sample "Berlin"
     */
    val authority: String?,

    /**
     * Max length: 100
     *
     * @sample "Berlin"
     */
    val placeOfIssue: String?,

    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    val countryOfDocument: String,

    )
