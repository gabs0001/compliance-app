package com.esg.compliance.api.mapper;

import com.esg.compliance.api.dto.audit.EnvironmentalAuditResponse;
import com.esg.compliance.domain.model.EnvironmentalAudit;

public class EnvironmentalAuditMapper {
    private EnvironmentalAuditMapper() {}

    public static EnvironmentalAuditResponse toResponse(EnvironmentalAudit audit) {
        return new EnvironmentalAuditResponse(
                audit.getId(),
                audit.getCompany().getId(),
                audit.getResult(),
                audit.getNotes(),
                audit.getAuditDate()
        );
    }
}