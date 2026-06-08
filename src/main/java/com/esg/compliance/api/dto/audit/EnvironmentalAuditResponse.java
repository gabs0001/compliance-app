package com.esg.compliance.api.dto.audit;

import com.esg.compliance.domain.enums.AuditResult;

import java.time.LocalDateTime;

public record EnvironmentalAuditResponse(
        Long id,
        Long companyId,
        AuditResult result,
        String notes,
        LocalDateTime auditDate
) {}