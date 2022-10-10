package com.broeskamp.postident.dto

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class SigningWebhook(
    val caseId: String,
    val referenceId: String?,

    val customData1: String?,
)
