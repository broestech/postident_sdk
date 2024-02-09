package com.broeskamp.postident.dto.request.ident

data class DrivingLicenceClass(
    /**
     * Max Length: 10
     *
     * @sample "A"
     */
    val className: String,
    /**
     * Max Length: 10
     *
     * @sample "1.1.2010"
     */
    val dateIssued: String,
    /**
     * Max Length: 10
     *
     * @sample "1.1.2010"
     */
    val dateOfExpiry: String,

    /** Max Length: 35 */
    val restrictions: String? = null
)