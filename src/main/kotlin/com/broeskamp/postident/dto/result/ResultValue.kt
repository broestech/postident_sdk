package com.broeskamp.postident.dto.result

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class ResultValue(
    val status: ResultValueStatus?,
    val value: String?,
)
