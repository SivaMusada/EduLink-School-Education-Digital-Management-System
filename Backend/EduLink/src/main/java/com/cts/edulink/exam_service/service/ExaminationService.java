package com.cts.edulink.exam_service.service;

import com.cts.edulink.exam_service.entity.*;
import com.cts.edulink.exam_service.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ExaminationService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private GradeRepository gradeRepository;

    // --- EXAM LOGIC ---
    public Exam saveExam(Exam exam) {
        log.info("Saving new exam for Course ID: {}", exam.getCourseID());
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Optional<Exam> getExamById(Long id) {
        return examRepository.findById(id);
    }

    public void deleteExam(Long id) {
        log.warn("Deleting exam ID: {}", id);
        examRepository.deleteById(id);
    }

    // --- GRADE LOGIC ---
    public Grade saveGrade(Grade grade) {
        // Validation: Check if the Exam exists before grading
        if (!examRepository.existsById(grade.getExamID())) {
            log.error("Failed to save grade: Exam ID {} not found", grade.getExamID());
            throw new RuntimeException("Exam ID does not exist!");
        }
        log.info("Posting grade for Student ID: {} in Exam ID: {}", grade.getStudentID(), grade.getExamID());
        return gradeRepository.save(grade);
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    public List<Grade> getGradesByStudent(Long studentId) {
        log.info("Fetching all grades for student: {}", studentId);
        return gradeRepository.findByStudentID(studentId);
    }

    public void deleteGrade(Long id) {
        log.warn("Deleting grade record ID: {}", id);
        gradeRepository.deleteById(id);
    }
}