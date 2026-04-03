package com.cts.edulink.reporting_service.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String scope;

    @Column(columnDefinition = "TEXT")
    private String metrics;

    private LocalDateTime generatedDate;
}