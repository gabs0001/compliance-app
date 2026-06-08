package com.esg.compliance.domain.model;

import com.esg.compliance.domain.enums.AuditResult;
import com.esg.compliance.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_ENVIRONMENTAL_AUDIT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnvironmentalAudit {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ENVIRONMENTAL_AUDIT"
    )
    @SequenceGenerator(
            name = "SEQ_ENVIRONMENTAL_AUDIT",
            sequenceName = "SEQ_ENVIRONMENTAL_AUDIT",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AuditResult result;

    @Column(length = 500)
    private String notes;

    @Column(name = "audit_date", insertable = false, updatable = false)
    private LocalDateTime auditDate;

    public static EnvironmentalAudit create(Company company, AuditResult result, String notes) {
        if(company == null) throw new BusinessException("Audit company is required.");
        if(result == null) throw new BusinessException("Audit result has to be informed");

        EnvironmentalAudit audit = new EnvironmentalAudit();
        audit.company = company;
        audit.result = result;
        audit.notes = notes;
        return audit;
    }

    public void updateNotes(String notes) { this.notes = notes; }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        EnvironmentalAudit that = (EnvironmentalAudit) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}