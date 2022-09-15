package com.broeskamp.postident.dto.request

data class CustomData(
    /**
     * Custom text field in order to pass your own identifiers, labels etc. You will get this information back in the result data.
     *
     * Max length: 100
     *
     * @sample "Kunden-Nr: 1234234"
     */
    val custom1: String?,
    val custom2: String?,
    val custom3: String?,
    val custom4: String?,
    val custom5: String?,
)
