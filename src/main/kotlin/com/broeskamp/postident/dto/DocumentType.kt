package com.broeskamp.postident.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Type of document. Represented as Number
 *
 * @sample "1"
 */
enum class DocumentType(private val postIdentAlias: String) {
    @JsonProperty("1")
    ID_CARD("1"),

    @JsonProperty("2")
    PASSPORT("2"),

    @JsonProperty("3")
    RESIDENCE_TITLE("3"),

    @JsonProperty("4")
    TEMPORARY_ID_CARD("4"),

    @JsonProperty("5")
    TEMPORARY_PASSPORT("5"),

    @JsonProperty("6")
    CONVENTION_TRAVEL_DOCUMENT("6"),

    @JsonProperty("7")
    CONVENTION_TRAVEL_DOCUMENT_REFUGEE("7"),

    @JsonProperty("8")
    CONVENTION_TRAVEL_DOCUMENT_FOREIGNER("8"),

    @JsonProperty("9")
    SERVICE_PASSPORT("9"),

    @JsonProperty("10")
    DIPLOMATIC_PASSPORT("10"),

    @JsonProperty("11")
    OFFICIAL_PASSPORT("11"),

    @JsonProperty("12")
    OFFICIAL_OR_DIPLOMATIC_PASSPORT("12");

    companion object {
        @JvmStatic
        fun byPostIdentAlias(alias: String): DocumentType =
            values().first { it.postIdentAlias == alias }
    }

}

