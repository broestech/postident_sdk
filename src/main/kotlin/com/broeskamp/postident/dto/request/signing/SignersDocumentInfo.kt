package com.broeskamp.postident.dto.request.signing

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class SignersDocumentInfo(

    /**
     * Index of the document to be signed by signer. The index is a pointer
     * in the documents list of the case. First document in the list has the
     * position 1.
     *
     * Max length: 50
     *
     * @sample 1
     */
    val documentToSign: Int?,

    /**
     * Name of the pdf form field that should be used for holding the signature
     * stamp. The field must be of type "signature" and have the size 72,5 mm x
     * 24,0 mm.
     *
     * This parameter takes precedence over signatureStampPosition
     *
     * Max length: 100
     */
    val signatureFieldName: String?,

    /** Position of the signature stamp */
    val signatureStampPosition: SignatureStampPosition?,

    )