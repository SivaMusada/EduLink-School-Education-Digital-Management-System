package com.cts.edulink.tracking_service.service;

import com.cts.edulink.tracking_service.entity.Attendance;
import com.cts.edulink.tracking_service.entity.PerformanceMetric;
import com.cts.edulink.tracking_service.repository.AttendanceRepository;
import com.cts.edulink.tracking_service.repository.PerformanceMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrackingService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    @Qualifier("trackingPerformanceRepository")
    private PerformanceMetricRepository metricRepository;

    public Attendance saveAttendance(Attendance a) { return attendanceRepository.save(a); }
    public List<Attendance> getAllAttendance() { return attendanceRepository.findAll(); }
    public Optional<Attendance> getAttendanceById(Long id) { return attendanceRepository.findById(id); }
    public List<Attendance> getAttendanceByStudent(Long studentId) { return attendanceRepository.findByStudentID(studentId); }
    public void deleteAttendance(Long id) { attendanceRepository.deleteById(id); }

    public PerformanceMetric saveMetric(PerformanceMetric m) { return metricRepository.save(m); }
    public List<PerformanceMetric> getAllMetrics() { return metricRepository.findAll(); }
    public Optional<PerformanceMetric> getMetricById(Long id) { return metricRepository.findById(id); }
    public void deleteMetric(Long id) { metricRepository.deleteById(id); }
}