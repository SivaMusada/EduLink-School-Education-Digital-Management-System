package com.cts.edulink.reporting_service.repository;


import com.cts.edulink.reporting_service.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
