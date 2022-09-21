package com.broeskamp.postident.dto.result.sign

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Possible outcomes:
 *
 * - unchecked: value has not been verified during the identification process. This happens for example if the identification document doesn't comprise a street address. In this case the value is returned which was passed in on case creation.
 * - new: value was not provided by client
 * - match: value as provided by client
 * - change: value was modified during identification process
 */
enum class ResultValueStatus {
    @JsonProperty("unchecked")
    UNCHECKED,

    @JsonProperty("new")
    NEW,

    @JsonProperty("match")
    MATCH,

    @JsonProperty("change")
    CHANGE,
}