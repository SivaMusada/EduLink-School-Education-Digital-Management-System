package com.cts.edulink.tracking_service.repository;

import com.cts.edulink.tracking_service.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentID(Long studentID);
}
