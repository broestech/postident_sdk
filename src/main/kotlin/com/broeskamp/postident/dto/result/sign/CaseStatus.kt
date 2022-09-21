package com.broeskamp.postident.dto.result.sign

import com.fasterxml.jackson.annotation.JsonProperty

enum class CaseStatus {
    @JsonProperty("new")
    NEW,
    
    @JsonProperty("signing process")
    SIGNING_PROCESS,

    @JsonProperty("signed")
    SIGNED,

    @JsonProperty("closed")
    CLOSED,

    @JsonProperty("declined")
    DECLINED
}