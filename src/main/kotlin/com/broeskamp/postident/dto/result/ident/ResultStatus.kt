package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class ResultStatus(
    val code: Number,
    val description: String,
)
