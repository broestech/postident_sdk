package com.broeskamp.postident.dto.result

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Substatus describing the status on a more detailed level. Possible values:
 *
 * - signerStatus 'declined':
 *      - signing declined
 *      - mobil phone number not verified
 *      - ident declined
 *      - maximum number of started operations exceeded
 *      - maximum number of created certificates exceeded
 *      - maximum number of sent sms exceeded
 *      - other signer was declined
 * - signerStatus 'declined', 'signing process'
 *      - prolongued duration of identification postprocess
 */
enum class SignerSubStatus {
    @JsonProperty("signing declined")
    SIGNING_DECLINED,

    @JsonProperty("mobil phone number not verified")
    MOBILE_PHONE_NUMBER_NOT_VERIFIED,

    @JsonProperty("ident declined")
    IDENT_DECLINED,

    @JsonProperty("maximum number of started operations exceeded")
    MAXIMUM_NUMBER_OF_STARTED_OPERATIONS_EXCEEDED,

    @JsonProperty("maximum number of created certificates exceeded")
    MAXIMUM_NUMBER_OF_CERTIFICATES_EXCEEDED,

    @JsonProperty("maximum number of sent sms exceeded")
    MAXIMUM_NUMBER_OF_SMS_EXCEEDED,

    @JsonProperty("other signer was declined")
    OTHER_SIGNER_WAS_DECLINED,

    @JsonProperty("prolongued duration of identification postprocess")
    PROLONGUED_DURATION_OF_IDENTIFICATION_POSTPROCESS
}


