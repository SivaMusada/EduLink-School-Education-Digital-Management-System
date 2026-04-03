package com.cts.edulink.reporting_service.repository;

import com.cts.edulink.reporting_service.entity.PerformanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("reportingPerformanceRepository")
public interface PerformanceReportRepository extends JpaRepository<PerformanceReport, Long> {

    @Query("SELECT AVG(p.score) FROM PerformanceReport p WHERE p.courseId = ?1")
    Double getAverageScoreByCourse(Long courseId);

    @Query("SELECT AVG(p.score) FROM PerformanceReport p WHERE p.studentId = ?1")
    Double getAverageScoreByStudent(Long studentId);

    @Query("SELECT COUNT(p) FROM PerformanceReport p WHERE p.courseId = ?1")
    Long countParticipantsByCourse(Long courseId);

    @Query("SELECT (COUNT(CASE WHEN p.score >= 40 THEN 1 END) * 100.0 / COUNT(p)) FROM PerformanceReport p WHERE p.courseId = ?1")
    Double calculatePassRateForExam(Long courseId);
}