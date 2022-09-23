package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder
import java.time.LocalDate
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Builder
data class DrivingLicenseProvided(
    /**
     * Max length: 20
     *
     * @sample A 12345678
     */
    val number: String?,

    /**
     * Max length: 35
     *
     * @sample "Max"
     */
    val firstName: String?,

    /**
     * Max length: 35
     *
     * @sample "Muster"
     */
    val lastName: String?,

    /**
     * Max length: 55
     *
     * @sample "Berlin"
     */
    val birthPlace: String?,

    val drivingLicenceClasses: List<Any>?,

    /**
     * Max length: 35
     */
    val restrictions: String?,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "2011-05-30"
     */
    val dateIssued: LocalDate?,

    /**
     * ISO 8601 format: YYYY-MM-DD
     *
     * Max length: 10
     *
     * @sample "2033-01-19"
     */
    val dateOfExpiry: LocalDate,

    /**
     * Max length: 100
     *
     * @sample "Berlin"
     */
    val authority: String?,

    /**
     * ISO-3166 ALPHA-3 plus RKS for Kosovo
     *
     * If the provided value is not valid, clear the field and try again without a countryOfDocument
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    val countryOfDocument: String,

    val records: List<RecordResult>?,
)
