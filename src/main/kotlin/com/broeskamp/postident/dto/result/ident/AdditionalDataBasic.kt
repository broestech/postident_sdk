package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder
import java.time.Instant

@Builder
data class AdditionalDataBasic(
    /**
     * Counter of coupon by Postident App requests
     *
     * @sample 1
     */
    val couponByPiAppRequestCount: Number,

    /**
     * ISO 8601 formatted timestamp of last coupon by Postident App request
     *
     * @sample 2016-01-28T23:59:59+01:00
     */
    val couponByPiAppRequestLastTimestamp: Instant,

    /**
     * Counter of coupon by third party App requests (SDK)
     *
     * @sample 1
     */
    val couponByTpAppRequestCount: Number,

    /**
     * ISO 8601 formatted timestamp of last coupon by third party App request (SDK)
     *
     * @sample 2016-01-28T23:59:59+01:00
     */
    val couponByTpAppRequestLastTimestamp: Instant,

    /**
     * Counter of coupon by Postident App requests
     *
     * @sample 1
     */
    val couponDownloadCount: Number,

    /**
     * ISO 8601 formatted timestamp of last coupon download
     *
     * @sample 2016-01-28T23:59:59+01:00
     */
    val couponDownloadLastTimestamp: Instant,

    /**
     * Counter of coupon by email requests
     *
     * @sample 1
     */
    val couponByEmailRequestCount: Number,
)
