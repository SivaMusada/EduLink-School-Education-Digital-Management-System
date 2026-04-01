package com.cts.edulink.student_service.service;

import com.cts.edulink.student_service.entity.StudentDocument;
import com.cts.edulink.student_service.repository.StudentDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentDocumentService {

    @Autowired
    private StudentDocumentRepository documentRepository;

    public StudentDocument uploadDocument(StudentDocument doc) {
        return documentRepository.save(doc);
    }

    public List<StudentDocument> getDocumentsByStudent(Long studentId) {
        return documentRepository.findByStudentID(studentId);
    }

    public Optional<StudentDocument> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public StudentDocument updateDocumentStatus(Long id, String status) {
        return documentRepository.findById(id).map(doc -> {
            doc.setVerificationStatus(status);
            return documentRepository.save(doc);
        }).orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}