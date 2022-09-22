package com.broeskamp.postident.dto.result.ident

import com.thinkinglogic.builder.annotation.Builder

@Builder
data class AddressMatch(
    /**
     * Is true if a German redirection order is found.
     */
    val mailRedirectionDomestic: Boolean,

    /**
     * Is true if a international redirection order is found.
     */
    val mailRedirectionInternational: Boolean,

    /**
     *Is true if the address is not available.
     */
    val undeliverable: Boolean,

    /**
     * Is true if the addressee is dead.
     */
    val addresseeDeceased: Boolean,
)
