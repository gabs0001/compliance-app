package com.esg.compliance.domain.enums;

public enum AuditResult {
    COMPLIANT,
    NON_COMPLIANT,
    APPROVED,
    IRREGULAR;

    public static AuditResult fromString(String value) {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("Audit result cannot be null or blank.");
        }

        try {
            return AuditResult.valueOf(value.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid audit result: " + value);
        }
    }
}