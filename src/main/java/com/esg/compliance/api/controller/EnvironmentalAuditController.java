package com.esg.compliance.api.controller;

import com.esg.compliance.api.dto.audit.*;
import com.esg.compliance.api.mapper.EnvironmentalAuditMapper;
import com.esg.compliance.domain.model.EnvironmentalAudit;
import com.esg.compliance.domain.service.EnvironmentalAuditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/audits")
@RequiredArgsConstructor
public class EnvironmentalAuditController {
    private final EnvironmentalAuditService auditService;

    @PostMapping
    public ResponseEntity<EnvironmentalAuditResponse> create(
            @RequestBody @Valid EnvironmentalAuditCreateRequest request
    ) {
        EnvironmentalAudit audit = auditService.create(
                request.companyId(),
                request.result(),
                request.notes()
        );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(audit.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(EnvironmentalAuditMapper.toResponse(audit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvironmentalAuditResponse> findById(@PathVariable Long id) {
        EnvironmentalAudit audit = auditService.findById(id);
        EnvironmentalAuditResponse response = EnvironmentalAuditMapper.toResponse(audit);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EnvironmentalAuditResponse>> findAll(
            @RequestParam(required = false) Long companyId
    ) {
        List<EnvironmentalAudit> list = (companyId != null)
                ? auditService.findByCompanyId(companyId)
                : auditService.findAll();

        return ResponseEntity.ok(list
                .stream()
                .map(EnvironmentalAuditMapper::toResponse)
                .toList()
        );
    }

    @PatchMapping("/{id}/notes")
    public ResponseEntity<EnvironmentalAuditResponse> updateNotes(
            @PathVariable Long id,
            @RequestBody @Valid EnvironmentalAuditNotesUpdateRequest request
    ) {
        EnvironmentalAudit updated = auditService.updateNotes(id, request.notes());
        return ResponseEntity.ok(EnvironmentalAuditMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        auditService.delete(id);
        return ResponseEntity.noContent().build();
    }
}