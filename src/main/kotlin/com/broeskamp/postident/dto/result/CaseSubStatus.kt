package com.broeskamp.postident.dto.result

/**
 * - for caseStatus declined:
 *      - validity period expired
 *      - signer was declined
 * - for caseStatus closed:
 *      - archived
 */
enum class CaseSubStatus {
    VALIDITY_PERIOD_EXPIRED,
    SIGNER_WAS_DECLINED,
    ARCHIVED
}
