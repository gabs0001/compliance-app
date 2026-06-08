package com.esg.compliance.api.controller;

import com.esg.compliance.api.dto.company.CompanyCreateRequest;
import com.esg.compliance.api.dto.company.CompanyResponse;
import com.esg.compliance.api.dto.company.CompanyNameUpdateRequest;
import com.esg.compliance.api.mapper.CompanyMapper;
import com.esg.compliance.domain.model.Company;
import com.esg.compliance.domain.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> create(
            @RequestBody @Valid CompanyCreateRequest request
    ) {
        Company company = companyService.create(request.name(), request.cnpj());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(company.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(CompanyMapper.toResponse(company));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> findById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        CompanyResponse response = CompanyMapper.toResponse(company);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> findAll() {
        List<CompanyResponse> list = companyService.findAll()
                .stream()
                .map(CompanyMapper::toResponse)
                .toList();

        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<CompanyResponse> updateName(
            @PathVariable Long id,
            @RequestBody @Valid CompanyNameUpdateRequest request
    ) {
        CompanyResponse response = CompanyMapper.toResponse(companyService.updateName(id, request.name()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}