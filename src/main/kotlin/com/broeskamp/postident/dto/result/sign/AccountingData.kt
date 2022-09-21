package com.broeskamp.postident.dto.result.sign

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class AccountingData(
    /**
     * Unique identifier for invoicing (dt. Abrechnungsnummer); also used as identifier of a client configuration
     *
     * Max length: 14
     *
     * @sample "37051234567891"
     */
    val accountingNumber: String?,

    /**
     * Product displayed on invoice, e.g. E-Signing
     *
     * Max length: 10
     *
     *  @sample "906800034"
     */
    val accountingProduct: String?,
)
