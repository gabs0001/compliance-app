package com.esg.compliance.api.controller;

import com.esg.compliance.api.dto.license.*;
import com.esg.compliance.api.mapper.EnvironmentalLicenseMapper;
import com.esg.compliance.domain.model.EnvironmentalLicense;
import com.esg.compliance.domain.service.EnvironmentalLicenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/licenses")
@RequiredArgsConstructor
public class EnvironmentalLicenseController {
    private final EnvironmentalLicenseService licenseService;

    @PostMapping
    public ResponseEntity<EnvironmentalLicenseResponse> create(
            @RequestBody @Valid EnvironmentalLicenseCreateRequest request
    ) {
        EnvironmentalLicense license = licenseService.create(
                request.companyId(),
                request.type(),
                request.issueDate(),
                request.expirationDate()
        );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(license.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(EnvironmentalLicenseMapper.toResponse(license));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvironmentalLicenseResponse> findById(@PathVariable Long id) {
        EnvironmentalLicense license = licenseService.findById(id);
        EnvironmentalLicenseResponse response = EnvironmentalLicenseMapper.toResponse(license);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EnvironmentalLicenseResponse>> findAll(
            @RequestParam(required = false) Long companyId
    ) {

        List<EnvironmentalLicense> list = (companyId != null)
                ? licenseService.findByCompany(companyId)
                : licenseService.findAll();

        return ResponseEntity.ok(list
                .stream()
                .map(EnvironmentalLicenseMapper::toResponse)
                .toList()
        );
    }

    @PatchMapping("/{id}/expiration")
    public ResponseEntity<EnvironmentalLicenseResponse> updateExpiration(
            @PathVariable Long id,
            @RequestBody @Valid EnvironmentalLicenseExpirationUpdateRequest request
    ) {
        EnvironmentalLicense updated = licenseService.updateExpiration(id, request.expirationDate());
        return ResponseEntity.ok(EnvironmentalLicenseMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        licenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}