package com.esg.compliance.domain.service;

import com.esg.compliance.domain.enums.AuditResult;
import com.esg.compliance.domain.model.Company;
import com.esg.compliance.domain.model.EnvironmentalAudit;
import com.esg.compliance.domain.repository.CompanyRepository;
import com.esg.compliance.domain.repository.EnvironmentalAuditRepository;
import com.esg.compliance.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentalAuditService {
    private final EnvironmentalAuditRepository auditRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public EnvironmentalAudit create(Long companyId, AuditResult result, String notes) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException("Company not found with id: " + companyId)
        );

        EnvironmentalAudit audit = EnvironmentalAudit.create(company, result, notes);

        return auditRepository.save(audit);
    }

    public EnvironmentalAudit findById(Long id) {
        return auditRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Audit not found with id: " + id)
        );
    }

    public List<EnvironmentalAudit> findAll() {
        return auditRepository.findAll();
    }

    public List<EnvironmentalAudit> findByCompanyId(Long companyId) {
        return auditRepository.findByCompanyId(companyId);
    }

    @Transactional
    public EnvironmentalAudit updateNotes(Long id, String notes) {
        EnvironmentalAudit audit = findById(id);

        audit.updateNotes(notes);

        return auditRepository.save(audit);
    }

    @Transactional
    public void delete(Long id) {
        EnvironmentalAudit audit = findById(id);
        auditRepository.delete(audit);
    }
}