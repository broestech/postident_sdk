package com.broeskamp.postident.dto.result.signing

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class AddressResult(
    /** Max value length: 55 */
    val streetAddress: ResultValue?,

    /** Max value length: 55 */
    val appendix: ResultValue?,

    /** Max value length: 11 */
    val postalCode: ResultValue?,

    /** Max value length: 55 */
    val city: ResultValue?,

    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * Max value length: 3
     */
    val country: ResultValue?,
)
