package com.esg.compliance.domain.model;

import com.esg.compliance.domain.enums.EnvironmentalLicenseStatus;
import com.esg.compliance.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_ENVIRONMENTAL_LICENSE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnvironmentalLicense {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ENVIRONMENTAL_LICENSE"
    )
    @SequenceGenerator(
            name = "SEQ_ENVIRONMENTAL_LICENSE",
            sequenceName = "SEQ_ENVIRONMENTAL_LICENSE",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    private Company company;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(name = "issue_date", nullable = false)
    private LocalDateTime issueDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, insertable = false)
    private EnvironmentalLicenseStatus status;

    public static EnvironmentalLicense create(
            Company company, String type, LocalDateTime issueDate, LocalDateTime expirationDate
    ) {
        validateDates(issueDate, expirationDate);
        if(company == null) throw new BusinessException("Company is required");
        if(type == null || type.isBlank()) throw new BusinessException("License type is required");

        EnvironmentalLicense license = new EnvironmentalLicense();
        license.company = company;
        license.type = type;
        license.issueDate = issueDate;
        license.expirationDate = expirationDate;

        return license;
    }

    public void updateExpirationDate(LocalDateTime newExpirationDate) {
        validateDates(this.issueDate, newExpirationDate);
        this.expirationDate = newExpirationDate;
    }

    private static void validateDates(LocalDateTime issue, LocalDateTime expiration) {
        if(issue == null || expiration == null)
            throw new BusinessException("Issue and expiration dates are required.");

        if(expiration.isBefore(issue))
            throw new BusinessException("The expiration date cannot be earlier than the issue date.");

    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        EnvironmentalLicense that = (EnvironmentalLicense) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}