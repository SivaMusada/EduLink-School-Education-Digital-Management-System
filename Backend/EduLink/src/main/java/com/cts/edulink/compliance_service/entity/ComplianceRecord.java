package com.cts.edulink.compliance_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ComplianceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complianceId;
    private Long entityId;
    private String type;
    private String result;
    private LocalDate date;
    private String notes;
}