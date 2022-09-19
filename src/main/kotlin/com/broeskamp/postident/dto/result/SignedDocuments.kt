package com.broeskamp.postident.dto.result

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class SignedDocuments(

    /**
     * Max length: 30
     */
    val referenceId: String?,

    /**
     * Max length: 35
     *
     * @sample "application/pdf"
     */
    val mimeType: String,

    /**
     * Must end with the file type .pdf; no special characters allowed
     *
     * Max length: 254
     *
     * @sample "mydocument.pdf"
     */
    val fileName: String,

    /**
     * base64 binary document data
     *
     * @sample "ABFuIG...VkPcD3="
     */
    val documentData: String
)