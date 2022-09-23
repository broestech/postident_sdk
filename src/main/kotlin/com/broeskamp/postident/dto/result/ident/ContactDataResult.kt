package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class ContactDataResult(

    /**
     * Free text field with academic title.
     *
     * @sample "Dr."
     */
    val title: ResultValue?,

    /**
     * All customer's given names separated by space.
     *
     * @sample "Thomas Christian"
     */
    val firstName: ResultValue,

    /**
     * Customer's family name.
     *
     * @sample "Mustermann"
     */
    val lastName: ResultValue,

    /**
     * Mobile phone number for the verification in the videochat
     *
     * Mandatory only for videochat in GWG
     *
     * @sample "+49160123456"
     */
    val mobilePhone: ResultValue?,

    /**
     * Email address of customer.
     *
     * @sample "thomas@mustermann.com"
     */
    val email: ResultValue,

    /**
     * Epost address of customer.
     *
     * @sample "thomas.mustermann@epost.de"
     */
    val epost: ResultValue?,

    /**
     * Customer's postal address.
     */
    val address: AddressResult,

)
