package com.esg.compliance.domain.service;

import com.esg.compliance.domain.model.Company;
import com.esg.compliance.domain.model.EnvironmentalLicense;
import com.esg.compliance.domain.repository.CompanyRepository;
import com.esg.compliance.domain.repository.EnvironmentalLicenseRepository;
import com.esg.compliance.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentalLicenseService {
    private final EnvironmentalLicenseRepository licenseRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public EnvironmentalLicense create(
            Long companyId,
            String type,
            LocalDateTime issueDate,
            LocalDateTime expirationDate
    ) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException("Company not found with ID: " + companyId)
        );

        EnvironmentalLicense license = EnvironmentalLicense.create(
                company,
                type,
                issueDate,
                expirationDate
        );

        return licenseRepository.save(license);
    }

    public EnvironmentalLicense findById(Long id) {
        return licenseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("License not found with ID: " + id)
        );
    }

    public List<EnvironmentalLicense> findAll() {
        return licenseRepository.findAll();
    }

    public List<EnvironmentalLicense> findByCompany(Long companyId) {
        return licenseRepository.findByCompanyId(companyId);
    }

    @Transactional
    public EnvironmentalLicense updateExpiration(Long id, LocalDateTime newDate) {
        EnvironmentalLicense license = findById(id);
        license.updateExpirationDate(newDate);

        return licenseRepository.save(license);
    }

    @Transactional
    public void delete(Long id) {
        EnvironmentalLicense license = findById(id);
        licenseRepository.delete(license);
    }
}