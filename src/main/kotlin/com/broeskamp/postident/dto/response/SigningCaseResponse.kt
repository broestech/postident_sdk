package com.broeskamp.postident.dto.response

data class SigningCaseResponse(

    /**
     * Unique Id for your signing case
     *
     * @sample "M3FB00URX4A3"
     */
    val caseId: String,

    val signers: List<Signer>,
)
