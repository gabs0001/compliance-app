package com.esg.compliance.api.mapper;

import com.esg.compliance.api.dto.emission.CarbonEmissionResponse;
import com.esg.compliance.domain.model.CarbonEmission;

public final class CarbonEmissionMapper {
    private CarbonEmissionMapper() {}

    public static CarbonEmissionResponse toResponse(CarbonEmission emission) {
        return new CarbonEmissionResponse(
                emission.getId(),
                emission.getCompany().getId(),
                emission.getAmount(),
                emission.getLimitValue(),
                emission.getStatus(),
                emission.getCreatedAt()
        );
    }
}