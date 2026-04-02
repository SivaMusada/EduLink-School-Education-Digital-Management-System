package com.cts.edulink.tracking_service.repository;

import com.cts.edulink.tracking_service.entity.PerformanceMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("trackingPerformanceRepository")
public interface PerformanceMetricRepository extends JpaRepository<PerformanceMetric, Long> {
    List<PerformanceMetric> findByStudentID(Long studentID);
}