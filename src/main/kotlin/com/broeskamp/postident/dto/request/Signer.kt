package com.broeskamp.postident.dto.request

import com.broeskamp.postident.dto.MultiPlatformUrl
import com.thinkinglogic.builder.annotation.Builder

@Builder
data class Signer(

    /**
     * Contact data of the person to sign the documents (user)
     */
    val contactData: ContactData,

    /**
     * Identity data of the person to sign the documents
     */
    val identityData: IdentityData,

    /**
     * Redirect or callback URL for user after success signing
     */
    val callbackUrlSigningSuccess: MultiPlatformUrl?,

    /**
     * Contains signer related data for the documents he has to sign.
     *
     * Using this parameter you can specify which documents the signer has to sign and the position of the signature of the stamp for this signer for each document.
     *
     * If you provide signerDocumentInfo Array this signer will see, sign and download only documents in this array. In this case each document has to be signed by at least one signer. If you want the signature stamp to be placed in only in one of your documents you have to specify which documents must be signed by this signer without the signature stamp.
     *
     * If you don't provide this parameter all signers will sign all documents and use the configuration provided in document (field). If in document no parameter are defined also, there will be no signature stamp on the pdf documents.
     */
    val documentsToSign: List<SignersDocumentInfo>?,

    )