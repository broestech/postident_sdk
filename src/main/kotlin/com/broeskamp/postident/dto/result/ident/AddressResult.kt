package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class AddressResult(
    /**
     * Contains street name and house number.
     *
     * @sample "Musterstreet 12"
     */
    val streetAddress: ResultValue?,

    /**
     * Contains a address appendix.
     *
     * @sample "Sales Department"
     */
    val appendix: ResultValue?,

    /**
     * Contains city's postal code.
     *
     * @sample "53113"
     */
    val postalCode: ResultValue?,

    /**
     * Name of city or town.
     *
     * @sample "Bonn"
     */
    val city: ResultValue?,

    /**
     * Country code accouding to ISO-3166 ALPHA-3 plus RKS for Kosovo.
     *
     * @sample "DEU"
     */
    val country: ResultValue?,

    /**
     * Result of address comparison (if needed).
     */
    val addressMatch: AddressMatch?,
)
