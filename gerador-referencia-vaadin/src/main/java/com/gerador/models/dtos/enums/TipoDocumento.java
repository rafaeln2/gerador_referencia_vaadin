package com.gerador.models.dtos.enums;

public enum TipoDocumento {
    BOOK_SECTION("book-section", "Book Section"),
    MONOGRAPH("monograph", "Monograph"),
    REPORT_COMPONENT("report-component", "Report Component"),
    REPORT("report", "Report"),
    PEER_REVIEW("peer-review", "Peer Review"),
    BOOK_TRACK("book-track", "Book Track"),
    JOURNAL_ARTICLE("journal-article", "Journal Article"),
    BOOK_PART("book-part", "Part"),
    OTHER("other", "Other"),
    BOOK("book", ""),
    JOURNAL_VOLUME("journal-volume", "Journal Volume"),
    BOOK_SET("book-set", "Book Set"),
    REFERENCE_ENTRY("reference-entry", "Reference Entry"),
    PROCEEDINGS_ARTICLE("proceedings-article", "Proceedings Article"),
    JOURNAL("journal", "Journal"),
    COMPONENT("component", "Component"),
    BOOK_CHAPTER("book-chapter", "Book Chapter"),
    PROCEEDINGS_SERIES("proceedings-series", "Proceedings Series"),
    REPORT_SERIES("report-series", "Report Series"),
    PROCEEDINGS("proceedings", "Proceedings"),
    DATABASE("database", "Database"),
    STANDARD("standard", "Standard"),
    REFERENCE_BOOK("reference-book", "Reference Book"),
    POSTED_CONTENT("posted-content", "Posted Content"),
    JOURNAL_ISSUE("journal-issue", "Journal Issue"),
    DISSERTATION("dissertation", "Dissertation"),
    GRANT("grant", "Grant"),
    DATASET("dataset", "Dataset"),
    BOOK_SERIES("book-series", "Book Series"),
    EDITED_BOOK("edited-book", "Edited Book");

    private final String value;
    private final String displayName;

    TipoDocumento(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TipoDocumento fromValue(String value) {
        for (TipoDocumento type : TipoDocumento.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}

