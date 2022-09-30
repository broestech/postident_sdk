package com.broeskamp.postident.dto.result.ident

import com.broeskamp.postident.dto.Address

data class ContactDataProvided(
    /**
     * Title, e.g. "Dr."
     *
     * Currently not used by POSTIDENT system
     *
     * Max length: 35
     *
     * @sample "Dr."
     */
    val title: String?,

    /**
     * Necessary for the identification process.
     *
     * If not provided, the fields firstName and lastName will be cleared as well, and the user will be asked.
     *
     * Max length: 35
     *
     * @sample "Max"
     */
    val firstName: String?,

    /**
     * Necessary for the identification process.
     *
     * If not provided, the fields firstName and lastName will be cleared as well, and the user will be asked.
     *
     * Max length: 35
     *
     * @sample "Muster"
     */
    val lastName: String?,

    /**
     * Country code plus phone number. Blanks are allowed and will be ignored.
     *
     * Necessary for the GWG identification process .
     *
     * If not provided, the field will be cleared as well, and the user will be asked
     *
     * Max length: 20
     *
     * @sample "Germany +49 171123456789, +49 171 123456789"
     * @sample "USA +1123456789010,+1 123 456789010"
     */
    val mobilePhone: String?,

    /**
     * Necessary for the identification process.
     *
     * If not provided, the field will be cleared as well, and the user will be asked.
     *
     * Max length: 320
     *
     * @sample "max.muster@gmail.com"
     */
    val email: String?,

    /**
     * Max length: 320
     *
     * @sample "max.muster@epost.de"
     */
    val epost: String?,

    /**
     * Address of the user.
     */
    val address: Address?

)
