package com.esg.compliance.api.dto.emission;

import com.esg.compliance.domain.enums.CarbonEmissionStatus;

import java.time.LocalDateTime;

public record CarbonEmissionResponse(
        Long id,
        Long companyId,
        Double amount,
        Double limitValue,
        CarbonEmissionStatus status,
        LocalDateTime createdAt
) {}