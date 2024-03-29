package com.broeskamp.postident.dto.request.signing

import com.broeskamp.postident.dto.CustomData
import com.thinkinglogic.builder.annotation.Builder


/** DTO to create a new SigningCase */
@Builder
data class SigningCaseRequest(

    /** Custom properties which will be sent back along with the result data */
    val customData: CustomData?,

    /** Properties which control the behavior, such as callback URLs. */
    val processData: SigningCaseProcessData,

    /**
     * Documents to be signed. All documents have to be marked with
     * hasToBeSigned = true. The upper limit for the total size of
     * all pdf documents belonging to the same signing case is 15 MB.
     */
    val documents: List<Document>,

    /**
     * Contains the data of the signers. There must be at least one signer.
     * Maximum number of signers is 5.
     */
    val signers: List<Signer>
)