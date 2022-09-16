package com.broeskamp.postident.dto.response

data class WebStart(
    /**
     * URL to redirect the browser of the user from your web portal to the E-Signing portal. The redirection must be executed as a HTTP Post that contains a JSON Web Token
     *
     * @sample "https://postident.deutschepost.de/signingportal/entry/e29c1298-5bff-4b0e-aa53-30b93d533840"
     */
    val caseUrl: String,


    /**
     * Base64 encoded 256-Bit secret for signing the JSON Web Token
     *
     * @sample "icEfRPW4exlKe0nDsXSHoyk7uQpupdFaFwWyT1Z8Ub8="
     */
    val redirectTokenSecret: String,

    /**
     * URL to let the user asynchronously resume the signing case for example after an interruption or waiting time. Send this URL via Email to your user to resume the signing case.
     *
     * @sample "https://postident.deutschepost.de/signingportal/reentry/e29c1298-5bff-4b0e-aa53-30b93d533840"
     */
    val resumeCaseURL: String,
)
