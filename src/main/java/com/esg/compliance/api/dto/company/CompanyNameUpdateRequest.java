package com.esg.compliance.api.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyNameUpdateRequest(
        @NotBlank(message = "Company name is required")
        @Size(max = 150)
        String name
) {}