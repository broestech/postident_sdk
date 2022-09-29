package com.broeskamp.postident.dto.result.ident

import com.broeskamp.postident.dto.Address
import com.broeskamp.postident.dto.DocumentType
import com.thinkinglogic.builder.annotation.Builder
import java.time.LocalDate

@Builder
data class IdentificationDocumentProvided(
    val type: DocumentType?,

    /**
     * Max length: 20
     */
    val number: String?,

    /**
     * All first names as printed on the identification document
     *
     * Max length: 35
     *
     * @sample "Max Walter"
     */
    val firstName: String?,

    /**
     * Exact last name as printed on identification document; may include title like "Dr."
     *
     * Max length: 35
     *
     * @sample "Muster"
     */
    val lastName: String?,

    /**
     * Only if differing from last name
     *
     * Do not include prefixes like „geb.“ or „Geborene“
     *
     * Max length: 35
     *
     * @sample "Meier"
     */
    val birthName: String?,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "1985-01-01"
     */
    val birthDate: LocalDate?,

    /**
     * Max length: 55
     *
     * @sample "Berlin"
     */
    val birthPlace: String?,

    /**
     * ISO-3166 ALPHA-3 plus
     *
     *      - RKS for Kosovar
     *      - XXA for Stateless
     *      - XXB for Refugee (1951 Convention)
     *      - XXC for Refugee
     *      - XXX for Unspecified
     *
     * If the provided value is not valid, clear the field and try again without a nationality
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    val nationality: String?,

    /**
     * Address of the user as printed on the identification document
     */
    val address: Address?,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "2010-05-20"
     */
    val dateIssued: LocalDate?,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "2010-05-19"
     */
    val dateOfExpiry: LocalDate?,

    /**
     * Max length: 100
     *
     * @sample "Berlin"
     */
    val authority: String?,

    /**
     * Field in German passport only
     *
     * Max length: 55
     *
     * @sample "Berlin"
     */
    val placeOfIssue: String?,

    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * If the provided value is not valid, clear the field and try again without a countryOfDocument
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    val countryOfDocument: String?,
)
