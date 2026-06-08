package com.esg.compliance.api.mapper;

import com.esg.compliance.api.dto.license.EnvironmentalLicenseResponse;
import com.esg.compliance.domain.model.EnvironmentalLicense;

public final class EnvironmentalLicenseMapper {
    private EnvironmentalLicenseMapper() {}

    public static EnvironmentalLicenseResponse toResponse(EnvironmentalLicense license) {
        return new EnvironmentalLicenseResponse(
                license.getId(),
                license.getCompany().getId(),
                license.getType(),
                license.getIssueDate(),
                license.getExpirationDate(),
                license.getStatus()
        );
    }
}