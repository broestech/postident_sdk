package com.broeskamp.postident.dto.result.signing

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class SignerContactData(
    /** Max value length: 20 */
    val mobilePhone: ResultValue,

    /** Max value length: 320 */
    val email: ResultValue,

    /** Max value length: 320 */
    val epost: ResultValue?,

    )
