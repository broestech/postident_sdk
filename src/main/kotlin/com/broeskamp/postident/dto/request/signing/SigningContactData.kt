package com.broeskamp.postident.dto.request.signing

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class SigningContactData(

    /**
     * Country code plus phone number. Must be unique in a signing case.
     *
     * Blanks are allowed and will be ignored.
     *
     * Must start with a country code beginning with "+".
     *
     * Max length: 320
     *
     * @sample German number "+49171123456789" or "+49 171 123456789"
     * @sample US number "+1123456789010" or "+1 123 456789010"
     */
    val mobilePhone: String,

    /**
     * Must be unique in a signing case.
     *
     * Max length: 320
     *
     * @sample "maria.mustermann@musterdomain.de"
     */
    val email: String,

    /** Max length: 320 */
    val epost: String?,

    )