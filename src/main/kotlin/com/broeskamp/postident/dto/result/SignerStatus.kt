package com.broeskamp.postident.dto.result

import com.fasterxml.jackson.annotation.JsonProperty

enum class SignerStatus {
    @JsonProperty("new")
    NEW,

    @JsonProperty("identification")
    IDENTIFICATION,

    @JsonProperty("signing process")
    SIGNING_PROCESS,

    @JsonProperty("signed")
    SIGNED,

    @JsonProperty("finished")
    FINISHED,

    @JsonProperty("declined'")
    DECLINED
}
