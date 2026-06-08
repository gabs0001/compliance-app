package com.esg.compliance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_ENVIRONMENTAL_ALERT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnvironmentalAlert {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ENVIRONMENTAL_ALERT"
    )
    @SequenceGenerator(
            name = "SEQ_ENVIRONMENTAL_ALERT",
            sequenceName = "SEQ_ENVIRONMENTAL_ALERT",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "reference_id", nullable = false, updatable = false)
    private Long referenceId;

    @Column(name = "reference_type", nullable = false, updatable = false)
    private String referenceType;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        EnvironmentalAlert that = (EnvironmentalAlert) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}