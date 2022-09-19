package com.broeskamp.postident.dto.request

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class IdentityData(

    /**
     * Max length: 35
     *
     * @sample "Dr."
     */
    val title: String?,

    /**
     * All first names of the person to sign the documents
     *
     * Max length: 35
     *
     * @sample "Maria"
     */
    val firstName: String,

    /**
     * Exact last name; may include title like "Dr."
     *
     * Max length: 35
     *
     * @sample "Musterfrau"
     */
    val lastName: String,

    /**
     * Only if differing from last name. Do not include prefixes like „geb.“ or „Geborene“
     *
     * Max length: 35
     *
     * @sample "Rossi"
     */
    val birthName: String?,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "1985-01-01"
     */
    val birthDate: String?,

    /**
     * Max length: 55
     *
     * @sample "Berlin"
     */
    val birthPlace: String?,

    /**
     * two-letter ISO3166-1 alpha-2, three-letter ISO3166-1 alpha-3 and RKS / XK for Kosovar
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    val nationality: String?,


    val address: Address?,

    /**
     * Flag to indicate if the provided data has already been verified by the client. If true the identification will be skipped. Requires a corresponding contractual agreement and special setup of client configuration.
     */
    val identityVerified: Boolean?,

    )