package com.cts.edulink.exam_service.controller;

import com.cts.edulink.exam_service.entity.*;
import com.cts.edulink.exam_service.service.ExaminationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExaminationController {

    @Autowired
    private ExaminationService examService;

    // --- EXAM ENDPOINTS ---

    @PostMapping
    public ResponseEntity<Exam> createExam(@Valid @RequestBody Exam exam) {
        return new ResponseEntity<>(examService.saveExam(exam), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Long id) {
        return examService.getExamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @Valid @RequestBody Exam details) {
        return examService.getExamById(id).map(exam -> {
            exam.setStatus(details.getStatus());
            exam.setDate(details.getDate());
            exam.setType(details.getType());
            return ResponseEntity.ok(examService.saveExam(exam));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.ok("Exam deleted successfully.");
    }

    // --- GRADE ENDPOINTS ---

    @PostMapping("/grades")
    public ResponseEntity<Grade> postGrade(@Valid @RequestBody Grade grade) {
        return new ResponseEntity<>(examService.saveGrade(grade), HttpStatus.CREATED);
    }

    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(examService.getAllGrades());
    }

    @GetMapping("/grades/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        return examService.getGradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/grades/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        List<Grade> grades = examService.getGradesByStudent(studentId);
        return grades.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(grades);
    }

    @DeleteMapping("/grades/{id}")
    public ResponseEntity<String> deleteGrade(@PathVariable Long id) {
        examService.deleteGrade(id);
        return ResponseEntity.ok("Grade record deleted.");
    }
}