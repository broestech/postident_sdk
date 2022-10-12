package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class IdentCaseResult(
    val caseId: String,
    val caseStatus: CaseStatus,
    val orderData: OrderData?,
    val contactData: ContactDataResult?,
    val identifications: List<IdentificationResult>,
    val accountingData: AccountingData,
)
