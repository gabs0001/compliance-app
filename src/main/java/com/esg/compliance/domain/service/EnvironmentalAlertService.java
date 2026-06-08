package com.esg.compliance.domain.service;

import com.esg.compliance.domain.model.EnvironmentalAlert;
import com.esg.compliance.domain.repository.EnvironmentalAlertRepository;
import com.esg.compliance.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentalAlertService {
    private final EnvironmentalAlertRepository alertRepository;

    public EnvironmentalAlert findById(Long id) {
        return alertRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No environment alert found with id: " + id)
        );
    }

    public List<EnvironmentalAlert> findAll() {
        return alertRepository.findAll();
    }

    public List<EnvironmentalAlert> findByReference(String referenceType, Long referenceId) {
        if(referenceType != null && referenceId != null) return alertRepository
                .findByReferenceTypeAndReferenceId(
                        referenceType.toUpperCase(), referenceId
                );

        if(referenceType != null) return alertRepository
                .findByReferenceType(referenceType.toUpperCase());

        if(referenceId != null) return alertRepository
                .findByReferenceId(referenceId);

        return alertRepository.findAll();
    }
}