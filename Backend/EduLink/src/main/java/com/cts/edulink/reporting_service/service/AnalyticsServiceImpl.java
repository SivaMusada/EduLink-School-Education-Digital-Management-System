package com.cts.edulink.reporting_service.service;


import com.cts.edulink.reporting_service.entity.Report;
import com.cts.edulink.reporting_service.repository.ReportRepository;
import com.cts.edulink.tracking_service.repository.PerformanceMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AnalyticsServiceImpl implements IAnalyticsService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    @Qualifier("trackingPerformanceRepository")
    private PerformanceMetricRepository metricRepository;

    @Override
    public Report generatePerformanceReport(String scope, Long targetId) {
        Report report = new Report();
        report.setScope(scope);
        report.setGeneratedDate(LocalDateTime.now());

        String metricsJson = switch (scope.toLowerCase()) {
            case "student" -> getDynamicStudentMetrics(targetId);
            case "course" -> getDynamicCourseMetrics(targetId);
            case "exam" -> getDynamicExamMetrics(targetId);
            default -> "{\"error\": \"Invalid scope\"}";
        };

        report.setMetrics(metricsJson);
        return reportRepository.save(report);
    }
    @Override
    public double calculatePassRate(Long courseId) {
        return 0;
    }

    private String getDynamicStudentMetrics(Long studentId) {
        Double avgScore = metricRepository.getAverageScoreByStudent(studentId);
        double finalScore = (avgScore != null) ? avgScore : 0.0;

        return String.format("{\"studentId\": %d, \"gpa\": %.2f}", studentId, finalScore);
    }

    private String getDynamicCourseMetrics(Long courseId) {
        Double courseAvg = metricRepository.getAverageScoreByCourse(courseId);
        long enrollmentCount = metricRepository.countParticipantsByCourse(courseId);

        return String.format("{\"courseId\": %d, \"averageScore\": %.2f, \"enrollment\": %d}",
                courseId, (courseAvg != null ? courseAvg : 0.0), enrollmentCount);
    }

    private String getDynamicExamMetrics(Long examId) {
        Double passRate = metricRepository.calculatePassRateForExam(examId);
        double finalPassRate = (passRate != null) ? passRate : 0.0;

        Long totalCandidates = metricRepository.countParticipantsByCourse(examId);
        long finalCount = (totalCandidates != null) ? totalCandidates : 0;

        return String.format("{\"examId\": %d, \"passRate\": %.2f, \"totalCandidates\": %d}",
                examId, finalPassRate, finalCount);
    }
}