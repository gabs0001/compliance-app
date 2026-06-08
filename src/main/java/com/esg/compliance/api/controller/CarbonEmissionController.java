package com.esg.compliance.api.controller;

import com.esg.compliance.api.dto.emission.*;
import com.esg.compliance.api.mapper.CarbonEmissionMapper;
import com.esg.compliance.domain.model.CarbonEmission;
import com.esg.compliance.domain.service.CarbonEmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emissions")
@RequiredArgsConstructor
public class CarbonEmissionController {
    private final CarbonEmissionService carbonEmissionService;

    @PostMapping
    public ResponseEntity<CarbonEmissionResponse> create(
            @RequestBody @Valid CarbonEmissionCreateRequest request
    ) {
        CarbonEmission emission = carbonEmissionService.create(
                request.companyId(),
                request.amount(),
                request.limitValue()
        );

        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(emission.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(CarbonEmissionMapper.toResponse(emission));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarbonEmissionResponse> findById(@PathVariable Long id) {
        CarbonEmission emission = carbonEmissionService.findById(id);
        CarbonEmissionResponse response = CarbonEmissionMapper.toResponse(emission);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CarbonEmissionResponse>> findAll(
            @RequestParam(required = false) Long companyId
    ) {
        List<CarbonEmission> emissions = (companyId != null)
                ? carbonEmissionService.findByCompanyId(companyId)
                : carbonEmissionService.findAll();

        List<CarbonEmissionResponse> response = emissions
                .stream()
                .map(CarbonEmissionMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carbonEmissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}