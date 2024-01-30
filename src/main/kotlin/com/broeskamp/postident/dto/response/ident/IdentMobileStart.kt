package com.broeskamp.postident.dto.response.ident

/** Start/Restart information for mobile apps (iOS and Android) */
data class IdentMobileStart(
    /**
     * Information to open POSTIDENT app in case it is already installed and
     * transmit the caseId
     *
     * @sample "postident://cases/{caseId}"
     */
    val caseUrlScheme: String,
    /**
     * Information to check, whether the POSTIDENT app is installed or not in
     * order to trigger the installation process.
     *
     * @sample "de.deutschepost.postident"
     */
    val packageName: String
)
