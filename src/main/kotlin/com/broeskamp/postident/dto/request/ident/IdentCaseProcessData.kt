package com.broeskamp.postident.dto.request.ident

import com.broeskamp.postident.dto.PreferredLanguage
import com.broeskamp.postident.dto.request.MultiplatformUrl
import com.broeskamp.postident.dto.request.ProcessData
import com.thinkinglogic.builder.annotation.Builder

@Builder
data class IdentCaseProcessData(
    override val targetCountry: String? = null,
    override val preferredLanguage: PreferredLanguage? = null,
    override val webHookUrl: String? = null,
    override val referenceId: String? = null,

    /**
     * Used by POSTIDENT to display a button for the user back to your
     * application. Provide a URL for each POSTIDENT platform that shall
     * display the redirect button. If not provided, the redirect button will
     * not be displayed.
     *
     * _Currently supported platforms: web_
     *
     * Max length: 500
     */
    val callbackUrlCouponRequested: MultiplatformUrl? = null,

    /**
     * Used by POSTIDENT to display a button for the user back to your
     * application. Provide a URL for each POSTIDENT platform that shall
     * display the redirect button. If not provided, the redirect button will
     * not be displayed.
     *
     * **For method autoid:**
     *
     * Callback URL for user in case there is no direct result from the machine
     * and an agent is required.
     *
     * _Currently supported platforms photo: iOS, Android_
     *
     * **For method photo:**
     *
     * Redirect or callback URL for user after photos taken and uploaded to be
     * processed by an agent.
     *
     * _Currently supported platforms: web, iOS, Android_
     *
     * Max Length: 500
     */
    val callbackUrlReviewPending: MultiplatformUrl? = null,
    /**
     * Used by POSTIDENT to display a button for the user back to your
     * application. Provide a URL for each POSTIDENT platform that shall
     * display the redirect button. If not provided, the redirect button will
     * not be displayed.
     *
     * **For method autoid:**
     * - In case of direct result from machine: Callback URL for user after
     *   success result.
     * - In case of result from agent: The Web URL will be included in the
     *   result E-Mail to user. This E-Mail is mandatory.
     *
     * _Currently supported platforms: iOS, Android_
     *
     * **For method eid:**
     * - Direct result from ID card reading: Redirect or callback URL for user
     *   after success result.
     *
     * _Currently supported platforms: web, iOS, Android_
     *
     * **For method photo:**
     * - Result from agent: The Web URL will be included in result EMail to
     *   user in case PI application is responsible for E-Mail communication.
     *   See Result Guide for more details.
     *
     * _Currently supported platforms: web, iOS, Android_
     *
     * **For method video:**
     * - Direct result from videochat: Redirect or callback URL after the
     *   videochat between agent and user has ended. Covers both success and
     *   declined result. Reason is, that at the end of chat the result is not
     *   yet fixed (preliminary result).
     *
     * _Currently supported platforms: web, iOS, Android_
     *
     * Max Length: 500
     */
    val callbackUrlSuccess: MultiplatformUrl? = null,

    /**
     * Used by POSTIDENT to display a button for the user back to your
     * application. Provide a URL for each POSTIDENT platform that shall
     * display the redirect button. If not provided, the redirect button will
     * not be displayed.
     *
     * **For method autoid:**
     * - In case of direct result from machine in app: Callback URL for user
     *   after declined result
     * - In case of result from agent: The Web URL will be included in the
     *   result E-Mail to user. This E-Mail is mandatory.
     *
     * _Currently supported platforms: iOS, Android_
     *
     * **For method eid:**
     * - Direct result from ID card reading: Callback URL for user after
     *   declined result.
     *
     * _Currently supported platforms: web, iOS, Android_
     *
     * **For method photo:**
     * - Result from agent: The Web URL will be included in result EMail to
     *   user in case PI application is responsible for E-Mail communication.
     *   See Result Guide for more details.
     *
     * _Currently supported platforms: web, iOS, Android_
     *
     * Max Length: 500
     */
    val callbackUrlDeclined: MultiplatformUrl? = null,

    ) : ProcessData()