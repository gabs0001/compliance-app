package com.esg.compliance.domain.repository;

import com.esg.compliance.domain.model.EnvironmentalAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvironmentalAuditRepository extends JpaRepository<EnvironmentalAudit, Long> {
    List<EnvironmentalAudit> findByCompanyId(Long companyId);
}