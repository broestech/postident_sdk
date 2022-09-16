package com.broeskamp.postident.dto.result

data class SignerContactData(
    /**
     * Max value length: 20
     */
    val mobilePhone: ResultValue,

    /**
     * Max value length: 320
     */
    val email: ResultValue,

    /**
     * Max value length: 320
     */
    val epost: ResultValue?,

    )
