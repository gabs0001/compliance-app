package com.esg.compliance.api.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyCreateRequest(
        @NotBlank(message = "Company name is required")
        @Size(max = 150)
        String name,

        @NotBlank(message = "CNPJ is required")
        @Size(min = 14, max = 18)
        String cnpj
) {}