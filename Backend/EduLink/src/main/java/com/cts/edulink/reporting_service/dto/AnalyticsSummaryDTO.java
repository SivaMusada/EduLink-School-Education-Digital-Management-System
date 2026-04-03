package com.cts.edulink.reporting_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AnalyticsSummaryDTO {
    private Long id;
    private String scope;
    private String metrics;
    private LocalDateTime generatedDate;
}