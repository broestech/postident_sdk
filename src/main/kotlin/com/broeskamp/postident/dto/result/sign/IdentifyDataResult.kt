package com.broeskamp.postident.dto.result.sign

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class IdentifyDataResult(
    /**
     * Max value length: 35
     */
    val title: ResultValue?,

    /**
     * Max value length: 55
     */
    val firstName: ResultValue,

    /**
     * Max value length: 55
     */
    val lastName: ResultValue,

    /**
     * Max value length: 55
     */
    val birthName: ResultValue?,

    /**
     * Max value length: 10
     */
    val birthDate: ResultValue,

    /**
     * Max value length: 55
     */
    val birthPlace: ResultValue,

    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * Max value length: 3
     */
    val nationality: ResultValue,

    val address: AddressResult?,
)
