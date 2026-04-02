package com.cts.edulink.exam_service.repository;

import com.cts.edulink.exam_service.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentID(Long studentID);
    List<Grade> findByExamID(Long examID);
}