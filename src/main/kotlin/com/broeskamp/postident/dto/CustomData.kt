package com.broeskamp.postident.dto

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class CustomData(
    /**
     * Custom text field in order to pass your own identifiers, labels etc. You will get this information back in the result data.
     *
     * Max length: 100
     *
     * @sample "Kunden-Nr: 1234234"
     */
    val custom1: String? = null,
    val custom2: String? = null,
    val custom3: String? = null,
    val custom4: String? = null,
    val custom5: String? = null
)
