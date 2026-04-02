package com.cts.edulink.tracking_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class PerformanceMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricID;

    private Long studentID;
    private Long courseID;
    private Double score;
    private LocalDate date;
    private String status;
}