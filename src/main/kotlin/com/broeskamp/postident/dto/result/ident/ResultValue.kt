package com.broeskamp.postident.dto.result.ident

import com.fasterxml.jackson.annotation.JsonProperty
import com.thinkinglogic.builder.annotation.Builder

@Builder
data class ResultValue (
    val status: ResultValueStatus?,
    val value: String?,
) {
    enum class ResultValueStatus {
        /**
         * "new" means that there was no contact data provided, the data is collected by the agent either from the identification document or from the identified person
         */
        @JsonProperty("new")
        NEW,

        /**
         * "match" means that contact data provided matches the data from the identification document or from the identified person
         */
        @JsonProperty("match")
        MATCH,

        /**
         * "change" means that contact data provided is overwritten by the data from the identification document or from the identified person.
         */
        @JsonProperty("change")
        CHANGE,
    }
}