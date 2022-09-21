package com.broeskamp.postident.dto.result.sign

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Type of document. Represented as Number
 *
 * @sample "1"
 */
enum class DocumentType {
    @JsonProperty("1")
    ID_CARD,

    @JsonProperty("2")
    PASSPORT,

    @JsonProperty("3")
    RESIDENCE_TITLE,

    @JsonProperty("4")
    TEMPORARY_ID_CARD,

    @JsonProperty("5")
    TEMPORARY_PASSPORT,

    @JsonProperty("6")
    CONVENTION_TRAVEL_DOCUMENT,

    @JsonProperty("7")
    CONVENTION_TRAVEL_DOCUMENT_REFUGEE,

    @JsonProperty("8")
    CONVENTION_TRAVEL_DOCUMENT_FOREIGNER,

    @JsonProperty("9")
    SERVICE_PASSPORT,

    @JsonProperty("10")
    DIPLOMATIC_PASSPORT,

    @JsonProperty("11")
    OFFICIAL_PASSPORT,

    @JsonProperty("12")
    OFFICIAL_OR_DIPLOMATIC_PASSPORT,
}

