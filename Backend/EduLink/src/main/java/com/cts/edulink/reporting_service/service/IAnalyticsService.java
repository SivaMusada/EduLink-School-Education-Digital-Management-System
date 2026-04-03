package com.cts.edulink.reporting_service.service;


import com.cts.edulink.reporting_service.entity.Report;

public interface IAnalyticsService {
    Report generatePerformanceReport(String scope, Long id);
    double calculatePassRate(Long courseId);
}