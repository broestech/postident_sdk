package com.broeskamp.postident.dto.result.ident

data class AccountingData(

    /**
     * Unique identifier for invoicing (German: Abrechnungsnummer)
     *
     * @sample "77777777173701"
     */
    val accountingNumber: String,

    /**
     * Product name printed on invoice
     *
     * @sample "Postident Video"
     */
    val accountingProduct: String,
)
