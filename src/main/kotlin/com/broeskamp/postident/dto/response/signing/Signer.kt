package com.broeskamp.postident.dto.response.signing

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class Signer(
    /**
     * Number of the signer
     *
     * @sample "1"
     */
    val signerNumber: Number,

    val webStart: SigningWebStart,
)
