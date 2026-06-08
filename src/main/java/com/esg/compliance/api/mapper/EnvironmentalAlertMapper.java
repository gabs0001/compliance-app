package com.esg.compliance.api.mapper;

import com.esg.compliance.api.dto.alert.EnvironmentalAlertResponse;
import com.esg.compliance.domain.model.EnvironmentalAlert;

public final class EnvironmentalAlertMapper {
    private EnvironmentalAlertMapper() {}

    public static EnvironmentalAlertResponse toResponse(EnvironmentalAlert alert) {
        return new EnvironmentalAlertResponse(
                alert.getId(),
                alert.getType(),
                alert.getDescription(),
                alert.getCreatedAt(),
                alert.getReferenceId(),
                alert.getReferenceType()
        );
    }
}