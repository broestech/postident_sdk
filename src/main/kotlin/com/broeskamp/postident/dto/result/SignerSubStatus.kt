package com.broeskamp.postident.dto.result

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
    SIGNING_DECLINED,
    MOBILE_PHONE_NUMBER_NOT_VERIFIED,
    IDENT_DECLINED,
    MAXIMUM_NUMBER_OF_STARTED_OPERATIONS_EXCEEDED,
    MAXIMUM_NUMBER_OF_CERTIFICATES_EXCEEDED,
    MAXIMUM_NUMBER_OF_SMS_EXCEEDED,
    OTHER_SIGNER_WAS_DECLINED,
    PROLONGUED_DURATION_OF_IDENTIFICATION_POSTPROCESS
}


