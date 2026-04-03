package com.cts.edulink.reporting_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_metrics")
@Data
public class PerformanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    private Long studentId;
    private Long courseId;
    private Double score;
    private LocalDateTime date;
    private String status;
}