package com.esg.compliance.domain.repository;

import com.esg.compliance.domain.model.EnvironmentalLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvironmentalLicenseRepository extends JpaRepository<EnvironmentalLicense, Long> {
    List<EnvironmentalLicense> findByCompanyId(Long companyId);
}