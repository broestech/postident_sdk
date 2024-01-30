package com.broeskamp.postident.dto.request.ident

import com.broeskamp.postident.dto.CustomData
import com.thinkinglogic.builder.annotation.Builder


/** DTO to create a new IdentCase */
@Builder
data class IdentCaseRequest(

    /** Properties which control the behavior, such as callback URLs. */
    val processData: IdentCaseProcessData,

    /**
     * Initial contact data of the user provided by the client (business
     * customer). Some of the parameters are necessary during the
     * identification process to contact the user by email and mobile phone:
     * firstName, lastName, mobilePhone, and email, we recommend to provide
     * this data if available. If not provided, the user will be asked by the
     * POSTIDENT system, before he can proceed with the identification process.
     */
    val contactDataProvided: IdentContactData,

    /**
     * Initial data of the identificationDocument provided by the client
     * (business customer). Please only provide this data if it's taken from
     * the identification document.
     */
    val identificationDocumentProvided: IdentDocumentData,

    val drivingLicenceProvided: IdentDrivingLicenceData? = null,

    /** Custom properties which will be sent back along with the result data */
    val customData: CustomData? = null,
)