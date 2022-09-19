package com.broeskamp.postident.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

enum class SigningButtonLabel {

    @JsonProperty("1")
    LEGAL_SIGNATURE,

    @JsonProperty("2")
    PAYMENT_SIGNATURE,

    @JsonProperty("3")
    DIGITAL_SIGNATURE,

    @JsonProperty("4")
    SIGN,

    @JsonProperty("5")
    PURCHASE
}