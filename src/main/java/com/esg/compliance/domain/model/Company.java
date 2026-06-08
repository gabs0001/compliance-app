package com.esg.compliance.domain.model;

import com.esg.compliance.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_COMPANY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_COMPANY"
    )
    @SequenceGenerator(
            name = "SEQ_COMPANY",
            sequenceName = "SEQ_COMPANY",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 18, updatable = false)
    private String cnpj;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public static Company create(String name, String cnpj) {
        validatePresence(name, "Company name is required");
        validatePresence(cnpj, "CNPJ is required");

        Company company = new Company();
        company.name = name;
        company.cnpj = cnpj;
        return company;
    }

    public void updateName(String name) {
        validatePresence(name, "New name cannot be empty");
        this.name = name;
    }

    private static void validatePresence(String value, String message) {
        if(value == null || value.isBlank()) {
            throw new BusinessException(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id != null && Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}