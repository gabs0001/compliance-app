package com.esg.compliance.api.dto.license;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EnvironmentalLicenseCreateRequest(
        @NotNull(message = "Company ID is required")
        Long companyId,

        @NotBlank(message = "License type is required")
        String type,

        @NotNull(message = "Issue date is required")
        LocalDateTime issueDate,

        @NotNull(message = "Expiration date is required")
        LocalDateTime expirationDate

) {}