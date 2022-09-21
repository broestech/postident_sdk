package com.broeskamp.postident.dto.result.sign

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class ResultValue(
    val status: ResultValueStatus?,
    val value: String?,
)
