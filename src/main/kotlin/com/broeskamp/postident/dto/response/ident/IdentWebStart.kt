package com.broeskamp.postident.dto.response.ident

data class IdentWebStart(
    /**
     * Url to start or restart the case in a web browser. Use this url to
     * redirect the browser of the user or send him an email with this URL. If
     * you send an email, do not forget to inform the user on the expiration
     * date of the URL.
     *
     * @sample "https://postident.deutschepost.de/user/start/?caseId=123456789ABC"
     */
    val caseURL: String
)
