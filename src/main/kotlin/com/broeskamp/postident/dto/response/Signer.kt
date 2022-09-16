package com.broeskamp.postident.dto.response

data class Signer(
    /**
     * Number of the signer
     *
     * @sample "1"
     */
    val signerNumber: String,

    val webStart: WebStart,
)
