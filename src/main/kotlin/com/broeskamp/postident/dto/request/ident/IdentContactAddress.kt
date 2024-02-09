package com.broeskamp.postident.dto.request.ident

data class IdentContactAddress(

    /**
     * Max Length: 70
     *
     * @sample "Musterstrasse 11a"
     */
    val streetAddress: String? = null,
    /**
     * Max Length: 55
     *
     * @sample "Hinterhaus"
     */
    val appendix: String? = null,
    /**
     * Max Length: 11
     *
     * @sample "53113"
     */
    val postalCode: String? = null,
    /**
     * Max Length: 55
     *
     * @sample "Bonn"
     */
    val city: String? = null,
    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * **If the provided value is not valid, clear the field and try again
     * without a country.**
     *
     * Max Length: 3
     *
     * @sample "DEU"
     */
    val country: String? = null
)
