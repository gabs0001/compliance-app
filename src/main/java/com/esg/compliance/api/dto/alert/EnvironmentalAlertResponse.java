package com.esg.compliance.api.dto.alert;

import java.time.LocalDateTime;

public record EnvironmentalAlertResponse(
        Long id,
        String type,
        String description,
        LocalDateTime createdAt,
        Long referenceId,
        String referenceType
) {}