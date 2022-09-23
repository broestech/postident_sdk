package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class AdditionalDataEid(
    val dkk: String,
)
