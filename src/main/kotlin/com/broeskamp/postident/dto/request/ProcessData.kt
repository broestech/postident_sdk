package com.broeskamp.postident.dto.request

import com.broeskamp.postident.dto.PreferredLanguage

/**
 * Abstract process data for the data both processes, ident and e-signing,
 * need.
 */
abstract class ProcessData {

    /**
     * Country from which the identification is requested. ISO 3166-1 ALPHA-3
     * plus RKS for Kosovo. If not provided, PI application will set the
     * default value DEU.
     *
     * Max length: 3
     *
     * @sample "DEU"
     */
    abstract val targetCountry: String?

    /**
     * Preferred language of the user, e.g. for emails Possible values:
     * - DE_DE
     * - EN_UK
     *
     * If not provided, PI application will set the default value DE_DE. NOTE:
     * EN_UK is not yet supported by E-Signing, PostIdent portal and app
     *
     * @sample "DE_DE"
     */
    abstract val preferredLanguage: PreferredLanguage?

    /**
     * URL for push notifications back to your application in case of
     * notifications or preliminary or final results. Only secure HTTPS URLs
     * are supported.
     *
     * Max length: 500
     *
     * @sample "https://democompany.com/api/piwebhook"
     */
    abstract val webHookUrl: String?

    /**
     * The reference id of the client. If provided, this must be unique in the
     * context of the client id
     *
     * Max length: 14
     *
     * @sample "K2345ASDF"
     */
    abstract val referenceId: String?
}