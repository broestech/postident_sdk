package com.broeskamp.postident.dto.request.ident

data class IdentDrivingLicenceData(
    /**
     * Max Length: 20
     *
     * @sample "A 12345678"
     */
    val number: String? = null,
    /**
     * All given names.
     *
     * Max Length: 35
     *
     * @sample "Max"
     */
    val firstName: String? = null,

    /**
     * Max Length: 35
     *
     * @sample "Muster
     */
    val lastName: String? = null,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max Length: 10
     *
     * @sample "1969-07-31"
     */
    val birthDate: String? = null,

    /** @sample "Berlin" */
    val birthPlace: String? = null,

    val drivingLicenseClasses: List<DrivingLicenceClass>? = null,

    /** Max Length: 35 */
    val restrictions: String? = null,
    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max Length: 10
     *
     * @sample "1969-07-31"
     */
    val dateIssued: String? = null,
    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max Length: 10
     *
     * @sample "1969-07-31"
     */
    val dateOfExpiry: String? = null,
    /**
     * Max Length: 100
     *
     * @sample "Stadt Bonn"
     */
    val authority: String? = null,
    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * **If the provided value is not valid, clear the field and try again
     * without a countryOfDocument.**
     *
     * Max Length: 3
     *
     * @sample "DEU"
     */
    val countryOfDocument: String? = null
)
