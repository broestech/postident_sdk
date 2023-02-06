package com.broeskamp.postident.dto.request

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class Document(

    /**
     * The display name of the document to be shown to the user in the signing process
     *
     * Max length: 50
     *
     * @sample "Kreditvertrag"
     */
    val name: String?,

    /**
     * External ID of the client for the document
     *
     * Max length: 30
     *
     * @sample "1243hiu023"
     */
    val referenceId: String?,

    /**
     * Mark with true if document has to be signed. All documents have to be marked with hasToBeSigned = true.
     */
    val hasToBeSigned: Boolean,

    /**
     * Mime type of the document. = ['application/pdf']
     *
     * Max length: 35
     *
     * @sample "application/pdf"
     */
    val mimeType: String,

    /**
     * Must end with the file type .pdf
     *
     * allowed characters: a-z,A-Z,0-9,_
     *
     * Max length: 254
     *
     * @sample "mydocument.pdf"
     */
    val fileName: String,

    /**
     * base64 binary document data
     *
     * Max size: 5MB
     *
     * @sample "TWFuIG...VyZS4="
     */
    val documentData: String,

    /**
     * Name of the pdf form field that should be used for holding the signature stamp. The field must be of type "signature" and have the size 72,5 mm x 24,0 mm.
     *
     * This field can be overwritten by the signatureFieldName in SignerDocumentInfo. This parameter takes precedence over signatureStampPosition.
     *
     * Max length: 10
     */
    val signatureFieldName: String?,

    /**
     * Position of the signature stamp. This Field could be overwritten by the signatureStamp Position in SignerDocumentInfo.
     */
    val signatureStampPosition: SignatureStampPosition?
)