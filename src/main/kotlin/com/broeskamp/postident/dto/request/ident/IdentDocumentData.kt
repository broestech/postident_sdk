package com.broeskamp.postident.dto.request.ident

data class IdentDocumentData(
    val type: IdentificationDocumentType?,

    /** @sample "T22000129" */
    val number: String? = null,

    /**
     * All first names as printed on the identification document
     *
     * Max Length: 35
     *
     * @sample "Max"
     */
    val firstName: String? = null,
    /**
     * Exact last name as printed on identification document; may include title
     * like "Dr."
     *
     * Max Length: 35
     *
     * @sample "Muster"
     */
    val lastName: String? = null,
    /**
     * Only if differing from last name. Do not include prefixes like "geb." or
     * "Geborene".
     *
     * Max Length: 35
     *
     * @sample "Meier"
     */
    val birthName: String? = null,
    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max Length: 10
     *
     * @sample "1969-07-31"
     */
    val birthDate: String? = null,
    /**
     * Birth place
     *
     * Max Length: 55
     *
     * @sample "Berlin"
     */
    val birthPlace: String? = null,
    /**
     * ISO-3166 ALPHA-3 plus
     * - RKS for Kosovar
     * - XXA for Stateless
     * - XXB for Refugee (1951 Convention)
     * - XXC for Refugee
     * - XXX for Unspecified
     *
     * **If the provided value is not valid, clear the field and try again
     * without a nationality.**
     *
     * Max Length: 3
     *
     * @sample "DEU"
     */
    val nationality: String? = null,

    /** Address of the user as printed on the identification document */
    val address: IdentContactAddress? = null,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max Length: 10
     *
     * @sample "2010-05-20"
     */
    val dateIssued: String? = null,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max Length: 10
     *
     * @sample "2010-05-20"
     */
    val dateOfExpiry: String? = null,

    /**
     * Max Length: 100
     *
     * @sample "Stadt Berlin"
     */
    val authority: String? = null,

    /**
     * Field in German passports only.
     *
     * Max Length: 55
     */
    val placeOfIssue: String? = null,
    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * Max Length: 3
     *
     * **If the provided value is not valid, clear the field and try again
     * without a countryOfDocument**
     *
     * @sample "DEU"
     */
    val countryOfDocument: String? = null
)
