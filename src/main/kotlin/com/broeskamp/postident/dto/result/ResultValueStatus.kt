package com.broeskamp.postident.dto.result

/**
 * Possible outcomes:
 *
 * - unchecked: value has not been verified during the identification process. This happens for example if the identification document doesn't comprise a street address. In this case the value is returned which was passed in on case creation.
 * - new: value was not provided by client
 * - match: value as provided by client
 * - change: value was modified during identification process
 */
enum class ResultValueStatus {
    UNCHECKED,
    NEW,
    MATCH,
    CHANGE,
}