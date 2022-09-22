package com.broeskamp.postident.dto.result.ident

import com.broeskamp.postident.dto.CustomData
import com.thinkinglogic.builder.annotation.Builder

@Builder
data class OrderData(
    /**
     *  custom properties, which will be sent back to you, along with the result data
     */
    val customData: CustomData?,

    val processData: ProcessData?,
    val contactDataProvided: ContactDataProvided?,
    val identificationDocumentProvided: IdentificationDocumentProvided?,
    val drivingLicenseProvided: DrivingLicenseProvided?,
)
