package com.broeskamp.postident.dto.response

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class SigningCaseResponse(

    /**
     * Unique Id for your signing case
     *
     * @sample "M3FB00URX4A3"
     */
    val caseId: String,

    val signers: List<Signer>,
)
