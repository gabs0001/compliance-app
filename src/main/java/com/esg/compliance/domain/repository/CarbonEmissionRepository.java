package com.esg.compliance.domain.repository;

import com.esg.compliance.domain.model.CarbonEmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarbonEmissionRepository extends JpaRepository<CarbonEmission, Long> {
    List<CarbonEmission> findByCompanyId(Long companyId);
}