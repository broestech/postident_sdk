package com.broeskamp.postident.dto.request

/**
 * The size of the signature stamp is 72,5 mm x 24,0 mm. The signature stamp expands from the top left corner.
 */
data class SignatureStampPosition(
    /**
     * the signature page index
     *
     * Max length: 10
     *
     * @sample 4
     */
    val pageNumber: Int?,

    /**
     * the x-coordinate of left edge of signature image (in units of 1/72 inches). Values are specified as offset from left margin of page
     *
     * Max length: 10
     *
     * @sample 380
     */
    val left: Int?,

    /**
     * the y-coordinate of top edge of signature image (in units of 1/72 inches). Values are specified as offset from top margin of page
     *
     * Max length: 10
     *
     * @sample 10
     */
    val top: Int?
)