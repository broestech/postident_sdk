package com.broeskamp.postident.dto.result

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * - for caseStatus declined:
 *      - validity period expired
 *      - signer was declined
 * - for caseStatus closed:
 *      - archived
 */
enum class CaseSubStatus {
    @JsonProperty("validity period expired")
    VALIDITY_PERIOD_EXPIRED,

    @JsonProperty("signer was declined")
    SIGNER_WAS_DECLINED,

    @JsonProperty("archived")
    ARCHIVED
}
