package com.cts.edulink.student_service.service;

import com.cts.edulink.exception.ResourceNotFoundException;
import com.cts.edulink.student_service.entity.Student;
import com.cts.edulink.student_service.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(Student student) {
        log.info("Registering new student with name: {}", student.getName());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        log.info("Fetching all students from database");
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        log.info("Fetching student details for ID: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public Student updateStudent(Long id, Student details) {
        log.info("Updating student details for ID: {}", id);
        return studentRepository.findById(id).map(student -> {
            student.setName(details.getName());
            student.setAddress(details.getAddress());
            student.setContactInfo(details.getContactInfo());
            student.setStatus(details.getStatus());
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("Update failed. Student not found with id " + id));
    }

    public void deleteStudent(Long id) {
        log.warn("Deleting student record for ID: {}", id);
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Delete failed. Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}