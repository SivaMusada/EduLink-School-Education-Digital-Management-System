package com.cts.edulink.exam_service.controller;

import com.cts.edulink.exam_service.entity.Exam;
import com.cts.edulink.exam_service.entity.Grade;
import com.cts.edulink.exam_service.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExaminationController {

    @Autowired
    private ExaminationService examService;

    // --- EXAM ENDPOINTS ---
    @PostMapping
    public Exam createExam(@RequestBody Exam exam) { return examService.saveExam(exam); }

    @GetMapping
    public List<Exam> getAllExams() { return examService.getAllExams(); }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Long id) {
        return examService.getExamById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam details) {
        return examService.getExamById(id).map(exam -> {
            exam.setStatus(details.getStatus());
            exam.setDate(details.getDate());
            return ResponseEntity.ok(examService.saveExam(exam));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.ok("Exam deleted.");
    }

    // --- GRADE ENDPOINTS ---
    @PostMapping("/grades")
    public Grade postGrade(@RequestBody Grade grade) { return examService.saveGrade(grade); }

    // --- GRADE ENDPOINTS ---

    // This prevents the "Failed to convert String to Long" error
// because it's a more specific match than /{id}
    @GetMapping("/grades")
    public List<Grade> getAllGrades() {
        return examService.getAllGrades();
    }

    @GetMapping("/grades/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        return examService.getGradeById(id) // Implement this in Service
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/grades/student/{studentId}")
    public List<Grade> getStudentGrades(@PathVariable Long studentId) {
        return examService.getGradesByStudent(studentId);
    }

    @DeleteMapping("/grades/{id}")
    public ResponseEntity<String> deleteGrade(@PathVariable Long id) {
        examService.deleteGrade(id);
        return ResponseEntity.ok("Grade deleted.");
    }
}