package com.broeskamp.postident.dto.result.ident

import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder

@Builder
data class IdentificationResult(
    val identificationMethod: IdentificationMethod,
    val identificationStatus: IdentificationStatus,
    val identificationDocument: IdentificationDocumentResult,
    val drivingLicense: DrivingLicenseProvided?,
    val records: List<RecordResult>?,
    val additionalDataBasic: AdditionalDataBasic?,
    val additionalDataVideo: AdditionalDataVideo?,
    val additionalDataEid: AdditionalDataEid?
) {
    enum class IdentificationMethod {
        @JsonProperty("photo")
        PHOTO,

        @JsonProperty("video")
        VIDEO,

        @JsonProperty("eid")
        E_ID,

        @JsonProperty("basic")
        BASIC,

        @JsonProperty("autoid")
        AUTO_ID,
    }
}
