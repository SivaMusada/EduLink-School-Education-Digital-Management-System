package com.cts.edulink.exam_service.service;

import com.cts.edulink.exam_service.entity.Exam;
import com.cts.edulink.exam_service.entity.Grade;
import com.cts.edulink.exam_service.repository.ExamRepository;
import com.cts.edulink.exam_service.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private GradeRepository gradeRepository;

    // Exam CRUD
    public Exam saveExam(Exam exam) { return examRepository.save(exam); }
    public List<Exam> getAllExams() { return examRepository.findAll(); }
    public Optional<Exam> getExamById(Long id) { return examRepository.findById(id); }
    public void deleteExam(Long id) { examRepository.deleteById(id); }

    // Grade CRUD
    public Grade saveGrade(Grade grade) { return gradeRepository.save(grade); }
    public List<Grade> getAllGrades() { return gradeRepository.findAll(); }
    public Optional<Grade> getGradeById(Long id) { return gradeRepository.findById(id); }
    public List<Grade> getGradesByStudent(Long studentId) { return gradeRepository.findByStudentID(studentId); }
    public void deleteGrade(Long id) { gradeRepository.deleteById(id); }
}