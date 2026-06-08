package com.esg.compliance.api.mapper;

import com.esg.compliance.api.dto.company.CompanyResponse;
import com.esg.compliance.domain.model.Company;

public final class CompanyMapper {
    private CompanyMapper() {}

    public static CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCnpj(),
                company.getCreatedAt()
        );
    }
}