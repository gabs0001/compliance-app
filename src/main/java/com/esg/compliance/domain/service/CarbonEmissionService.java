package com.esg.compliance.domain.service;

import com.esg.compliance.domain.model.CarbonEmission;
import com.esg.compliance.domain.model.Company;
import com.esg.compliance.domain.repository.CarbonEmissionRepository;
import com.esg.compliance.domain.repository.CompanyRepository;
import com.esg.compliance.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarbonEmissionService {
    private final CarbonEmissionRepository carbonEmissionRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public CarbonEmission create(Long companyId, Double amount, Double limitValue) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException("Company not found with ID: " + companyId));

        CarbonEmission emission = CarbonEmission.create(company, amount, limitValue);

        return carbonEmissionRepository.save(emission);
    }

    public CarbonEmission findById(Long id) {
        return carbonEmissionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Emission not found with ID: " + id)
        );
    }

    public List<CarbonEmission> findAll() {
        return carbonEmissionRepository.findAll();
    }

    public List<CarbonEmission> findByCompanyId(Long companyId) {
        return carbonEmissionRepository.findByCompanyId(companyId);
    }

    @Transactional
    public void delete(Long id) {
        CarbonEmission emission = findById(id);
        carbonEmissionRepository.delete(emission);
    }
}