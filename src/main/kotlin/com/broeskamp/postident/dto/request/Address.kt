package com.broeskamp.postident.dto.request

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class Address(

    /**
     * May include street name, house number, Post Office Box
     *
     * Max length: 55
     *
     * @sample "Musterstraße 2"
     */
    val streetAddress: String?,

    /**
     * Max length: 55
     *
     * @sample "Am Vorderhaus"
     */
    val appendix: String?,

    /**
     * Max length: 11
     *
     * @sample "51456"
     */
    val postalCode: String?,

    /**
     * Max length: 55
     *
     * @sample "Berlin"
     */
    val city: String?,

    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    val country: String?,

    )