package com.cts.edulink.reporting_service.controller;


import com.cts.edulink.reporting_service.entity.Report;
import com.cts.edulink.reporting_service.service.IAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private IAnalyticsService analyticsService;

    @GetMapping("/generate/{scope}")
    public ResponseEntity<Report> generate(
            @PathVariable String scope,
            @RequestParam(required = false, defaultValue = "1") Long id) {

        return ResponseEntity.ok(analyticsService.generatePerformanceReport(scope,id));
    }
}