package com.esg.compliance.api.dto.company;

import java.time.LocalDateTime;

public record CompanyResponse(
        Long id,
        String name,
        String cnpj,
        LocalDateTime createdAt
) {}