package com.broeskamp.postident.dto.request.ident

data class IdentContactData(
    /**
     * Title, e.g. "Dr."
     *
     * Currently not used by POSTIDENT system
     *
     * Max Length: 35
     *
     * @sample "Dr."
     */
    val title: String? = null,

    /**
     * Necessary for the identification process.
     *
     * **If not provided, the field lastName will be cleared as well, and the
     * user will be asked.**
     *
     * Max Length: 35
     *
     * @sample "Max"
     */
    val firstName: String? = null,

    /**
     * Necessary for the identification process.
     *
     * **If not provided, the field firstName will be cleared as well, and the
     * user will be asked.**
     *
     * Max Length: 35
     *
     * @sample "Muster"
     */
    val lastName: String? = null,
    /**
     * Country code plus phone number. Blanks are allowed and will be ignored.
     *
     * Necessary for the GWG identification process .
     *
     * **If not provided, the field will be cleared as well, and the user will
     * be asked.**
     *
     * Max Length: 20
     *
     * @sample "+49 171123456789"
     */
    val mobilePhone: String? = null,

    /**
     * Necessary for the identification process.
     *
     * **If not provided the user will be asked.**
     *
     * Max Length: 320
     *
     * @sample "max.muster@gmail.com
     */
    val email: String? = null,

    /** @sample "max.muster@epost.de" */
    val epost: String? = null,

    /** Address of the user. */
    val address: IdentContactAddress? = null
)