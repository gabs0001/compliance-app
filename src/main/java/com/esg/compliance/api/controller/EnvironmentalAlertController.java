package com.esg.compliance.api.controller;

import com.esg.compliance.api.dto.alert.EnvironmentalAlertResponse;
import com.esg.compliance.api.mapper.EnvironmentalAlertMapper;
import com.esg.compliance.domain.model.EnvironmentalAlert;
import com.esg.compliance.domain.service.EnvironmentalAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class EnvironmentalAlertController {
    private final EnvironmentalAlertService alertService;

    @GetMapping("/{id}")
    public ResponseEntity<EnvironmentalAlertResponse> findById(@PathVariable Long id) {
        EnvironmentalAlert alert = alertService.findById(id);
        EnvironmentalAlertResponse response = EnvironmentalAlertMapper.toResponse(alert);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EnvironmentalAlertResponse>> findAll(
            @RequestParam(required = false) String referenceType,
            @RequestParam(required = false) Long referenceId
    ) {

        List<EnvironmentalAlert> alerts = alertService.findByReference(referenceType, referenceId);

        List<EnvironmentalAlertResponse> response = alerts
                .stream()
                .map(EnvironmentalAlertMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}