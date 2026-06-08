package com.esg.compliance.domain.model;

import com.esg.compliance.domain.enums.CarbonEmissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_CARBON_EMISSION")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarbonEmission {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CARBON_EMISSION"
    )
    @SequenceGenerator(
            name = "SEQ_CARBON_EMISSION",
            sequenceName = "SEQ_CARBON_EMISSION",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    private Company company;

    @Column(nullable = false, updatable = false)
    private Double amount;

    @Column(name = "limit_value", nullable = false, updatable = false)
    private Double limitValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, insertable = false)
    private CarbonEmissionStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static CarbonEmission create(Company company, Double amount, Double limitValue) {
        validateInputs(company, amount, limitValue);

        CarbonEmission emission = new CarbonEmission();
        emission.company = company;
        emission.amount = amount;
        emission.limitValue = limitValue;

        return emission;
    }

    private static void validateInputs(Company company, Double amount, Double limitValue) {
        if(company == null) throw new IllegalArgumentException("Company is required.");
        if(amount == null || amount < 0) throw new IllegalArgumentException("Emission amount is required.");
        if(limitValue == null || limitValue < 0) throw new IllegalArgumentException("Limit value is required.");
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        CarbonEmission that = (CarbonEmission) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}