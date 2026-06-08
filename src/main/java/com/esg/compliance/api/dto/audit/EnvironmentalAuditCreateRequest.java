package com.esg.compliance.api.dto.audit;

import com.esg.compliance.domain.enums.AuditResult;
import jakarta.validation.constraints.NotNull;

public record EnvironmentalAuditCreateRequest(
        @NotNull(message = "Company ID is required")
        Long companyId,

        @NotNull(message = "Audit result is required")
        AuditResult result,

        String notes
) {}