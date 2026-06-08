package com.esg.compliance.api.dto.emission;

import jakarta.validation.constraints.*;

public record CarbonEmissionCreateRequest(
        @NotNull(message = "Company ID is required")
        Long companyId,

        @NotNull(message = "Amount is required")
        @PositiveOrZero(message = "Amount must be >= 0")
        Double amount,

        @NotNull(message = "Limit value is required")
        @PositiveOrZero(message = "Limit must be >= 0")
        Double limitValue
) {}