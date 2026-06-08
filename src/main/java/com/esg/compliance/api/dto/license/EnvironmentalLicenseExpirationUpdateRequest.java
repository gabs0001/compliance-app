package com.esg.compliance.api.dto.license;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EnvironmentalLicenseExpirationUpdateRequest(
        @NotNull(message = "New expiration date is required")
        LocalDateTime expirationDate
) {}