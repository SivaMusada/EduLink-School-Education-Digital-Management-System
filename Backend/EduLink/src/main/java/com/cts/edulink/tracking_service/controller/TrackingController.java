package com.cts.edulink.tracking_service.controller;

import com.cts.edulink.tracking_service.entity.Attendance;
import com.cts.edulink.tracking_service.entity.PerformanceMetric;
import com.cts.edulink.tracking_service.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @PostMapping("/attendance")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return trackingService.saveAttendance(attendance);
    }

    @GetMapping("/attendance")
    public List<Attendance> getAllAttendance() {
        return trackingService.getAllAttendance();
    }

    @GetMapping("/attendance/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        return trackingService.getAttendanceById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/attendance/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance details) {
        return trackingService.getAttendanceById(id).map(a -> {
            a.setStatus(details.getStatus());
            a.setDate(details.getDate());
            return ResponseEntity.ok(trackingService.saveAttendance(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        trackingService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record " + id + " deleted.");
    }

    @PostMapping("/performance")
    public PerformanceMetric addMetric(@RequestBody PerformanceMetric metric) {
        return trackingService.saveMetric(metric);
    }

    @GetMapping("/performance")
    public List<PerformanceMetric> getAllMetrics() {
        return trackingService.getAllMetrics();
    }

    @DeleteMapping("/performance/{id}")
    public ResponseEntity<String> deleteMetric(@PathVariable Long id) {
        trackingService.deleteMetric(id);
        return ResponseEntity.ok("Metric record " + id + " deleted.");
    }
}