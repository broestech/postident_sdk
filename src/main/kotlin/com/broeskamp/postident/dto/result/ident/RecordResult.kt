package com.broeskamp.postident.dto.result.ident

import com.fasterxml.jackson.annotation.JsonProperty

data class RecordResult(

    /**
     * PI system-wide unique id of the record
     */
    val recordId: String,

    val belongsTo: BelongsTo,

    val type: Type,

    /**
     * Only for photo:
     */
    val validationStatus: ValidationStatus?,

    /**
     * Only for photo:
     */
    val validationSubStatus: ValidationSubStatus?,

    /**
     * Only for photo:
     */
    val validationRemark: String?,

    /**
     * Required for display and file extension in file name
     */
    val mimeType: String,

    /**
     * Format:
     *
     *      - result pdf: <caseId>_<type>_<method>.<file extension>
     *      - videochatrecording: <caseId>_videochatrecording.webm
     *      - other: <caseId>_<type>.<file extension>
     *
     * To avoid duplicates a sequential number may be added
     *
     * @sample "12345678ABCD_result_video.pdf"
     * @sample "12345678ABCD_idimage.jpg"
     * @sample "12345678ABCD_idimage_2.jpg"
     */
    val fileName: String,

    /**
     * base64 binary document data.
     *
     * **Note**
     *
     * IdentificationDocument images are only delivered for class GwG.
     *
     * Videochatrecording are only delivered via SFTP and only for class GwG.
     */
    val documentData: String,

) {
    enum class BelongsTo {

        @JsonProperty("method")
        METHOD,

        @JsonProperty("identificationdocument")
        IDENTIFICATION_DOCUMENT,

        @JsonProperty("drivinglicence")
        DRIVING_LICENCE
    }

    /**
     * Short description of content; part of filename
     */
    enum class Type {

        /**
         * Types for method:
         */
        @JsonProperty("result")
        RESULT,

        @JsonProperty("resultdata")
        RESULT_DATA,

        @JsonProperty("resultimages")
        RESULT_IMAGES,

        @JsonProperty("userface")
        USERFACE,

        @JsonProperty("usersignature")
        USER_SIGNATURE,

        @JsonProperty("videochatrecording")
        VIDEOCHAT_RECORDING,

        /**
         * Types for identificationDocument:
         */
        @JsonProperty("idfrontside")
        ID_FRONTSIDE,

        @JsonProperty("idbackside")
        ID_BACKSIDE,

        @JsonProperty("idimage")
        ID_IMAGE,

        /**
         * Types for drivingLicence:
         */
        @JsonProperty("idsignature")
        ID_SIGNATURE,

        @JsonProperty("dlfrontside")
        DL_FRONTSIDE,

        @JsonProperty("dlbackside")
        DL_BACKSIDE,

        @JsonProperty("dlimage")
        DL_IMAGE,
    }

    enum class ValidationStatus {

        @JsonProperty("unchecked")
        UNCHECKED,

        @JsonProperty("valid")
        VALID,

        @JsonProperty("invalid")
        INVALID
    }

    enum class ValidationSubStatus {

        @JsonProperty("document not supported")
        DOCUMENT_NOT_SUPPORTED,

        @JsonProperty("document not identifiable")
        DOCUMENT_NOT_IDENTIFIABLE,

        @JsonProperty("document expired")
        DOCUMENT_EXPIRED,

        @JsonProperty("fraud suspicion")
        FRAUD_SUSPICION,

        @JsonProperty("abuse")
        ABUSE,

        @JsonProperty("person not identifiable")
        PERSON_NOT_IDENTIFIABLE,

        @JsonProperty("bad exposure")
        BAD_EXPOSURE,

        @JsonProperty("too blurry")
        TOO_BLURRY,

        @JsonProperty("partly concealed")
        PARTLY_CONCEALED,

        @JsonProperty("picture detail not sufficient")
        PICTURE_DETAIL_NOT_SUFFICIENT,
    }
}
