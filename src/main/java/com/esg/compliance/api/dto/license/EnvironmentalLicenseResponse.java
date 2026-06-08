package com.esg.compliance.api.dto.license;

import com.esg.compliance.domain.enums.EnvironmentalLicenseStatus;

import java.time.LocalDateTime;

public record EnvironmentalLicenseResponse(
        Long id,
        Long companyId,
        String type,
        LocalDateTime issueDate,
        LocalDateTime expirationDate,
        EnvironmentalLicenseStatus status
) {}