package com.esg.compliance.domain.enums;

public enum EnvironmentalLicenseStatus {
    ACTIVE,
    EXPIRED,
    VALID,
    RENEWAL_REQUIRED;

    public static EnvironmentalLicenseStatus fromString(String value) {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("Environmental license status cannot be null or blank.");
        }

        try {
            return EnvironmentalLicenseStatus.valueOf(value.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid environmental license status: " + value);
        }
    }
}