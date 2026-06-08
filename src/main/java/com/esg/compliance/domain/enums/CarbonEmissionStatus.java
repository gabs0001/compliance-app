package com.esg.compliance.domain.enums;

public enum CarbonEmissionStatus {
    EXCEEDED,
    ABOVE_LIMIT,
    NORMAL;

    public static CarbonEmissionStatus fromString(String value) {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("Carbon Emission Status cannot be null or blank.");
        }

        try {
            return CarbonEmissionStatus.valueOf(value.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid carbon emission status: " + value);
        }
    }
}