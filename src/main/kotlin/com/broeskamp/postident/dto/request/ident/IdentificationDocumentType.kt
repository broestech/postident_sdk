package com.broeskamp.postident.dto.request.ident

enum class IdentificationDocumentType(val apiValue: Int) {

    /** Personalausweis / ID Card */
    ID_CARD(1),

    /** Reisepass / Passport */
    PASSPORT(2),

    /** Aufenthaltstitel / Residence Title */
    RESIDENCE_TITLE(3),

    /** vorläufig ausgestellter Personalausweis / Temporary ID-Card */
    TEMPORARY_ID_CARD(4),

    /** vorläufig ausgestellter Reisepass / Temporary Passport */
    TEMPORARY_PASSPORT(5),

    /**
     * Reiseausweis für Staatenlose (Übereinkommen von 1954) / Convention
     * Travel Document 1954 (Stateless Person)
     */
    CONVENTION_TRAVEL_DOCUMENT_1954(6),

    /**
     * Reiseausweis für Flüchtlinge (Übereinkommen von 1951) / Convention
     * Travel Document 1951 (Refugee)
     */
    CONVENTION_TRAVEL_DOCUMENT_1951(7),

    /** Reiseausweis für Ausländer / Travel Document For Aliens / Foreigners */
    TRAVEL_DOCUMENT_FOR_ALIENS_OR_FOREIGNERS(8),

    /** Dienstpass / Service Passport */
    SERVICE_PASSPORT(9),

    /** Diplomatenpass / Diplomatic Passport */
    DIPLOMATIC_PASSPORT(10),

    /** Ministerialpass / Official Passport */
    OFFICIAL_PASSPORT(11),
}