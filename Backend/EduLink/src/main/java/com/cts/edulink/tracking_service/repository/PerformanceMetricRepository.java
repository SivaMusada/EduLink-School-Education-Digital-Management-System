package com.cts.edulink.tracking_service.repository;

import com.cts.edulink.tracking_service.entity.PerformanceMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("trackingPerformanceRepository")
public interface PerformanceMetricRepository extends JpaRepository<PerformanceMetric, Long> {
    List<PerformanceMetric> findByStudentID(Long studentID);
    @Query("SELECT AVG(p.score) FROM PerformanceMetric p WHERE p.courseID = ?1")
    Double getAverageScoreByCourse(Long courseID);

    @Query("SELECT AVG(p.score) FROM PerformanceMetric p WHERE p.studentID = ?1")
    Double getAverageScoreByStudent(Long studentID);

    @Query("SELECT COUNT(p) FROM PerformanceMetric p WHERE p.courseID = ?1")
    Long countParticipantsByCourse(Long courseID);

    @Query("SELECT (COUNT(CASE WHEN p.score >= 40 THEN 1 END) * 100.0 / COUNT(p)) FROM PerformanceMetric p WHERE p.courseID = ?1")
    Double calculatePassRateForExam(Long courseID);
}