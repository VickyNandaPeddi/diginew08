package com.aashdit.digiverifier.config.superadmin.Enum;

public enum ReportType {
    PRE_OFFER("Pre Offer"),
    INTERIM("Interim"),
    FINAL("Final");

    public final String label;

    private ReportType(String label) {
        this.label = label;
    }


}
