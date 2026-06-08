package com.esg.compliance.domain.repository;

import com.esg.compliance.domain.model.EnvironmentalAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvironmentalAlertRepository extends JpaRepository<EnvironmentalAlert, Long> {
    List<EnvironmentalAlert> findByReferenceType(String referenceType);
    List<EnvironmentalAlert> findByReferenceId(Long referenceId);
    List<EnvironmentalAlert> findByReferenceTypeAndReferenceId(String referenceType, Long referenceId);
}