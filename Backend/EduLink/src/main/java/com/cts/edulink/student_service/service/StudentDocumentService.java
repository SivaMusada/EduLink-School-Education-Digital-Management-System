package com.cts.edulink.student_service.service;

import com.cts.edulink.exception.ResourceNotFoundException;
import com.cts.edulink.student_service.entity.StudentDocument;
import com.cts.edulink.student_service.repository.StudentDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class StudentDocumentService {

    @Autowired
    private StudentDocumentRepository documentRepository;

    public StudentDocument uploadDocument(StudentDocument doc) {
        log.info("Uploading document type: {} for Student ID: {}", doc.getDocType(), doc.getStudentID());
        return documentRepository.save(doc);
    }

    public List<StudentDocument> getDocumentsByStudent(Long studentId) {
        log.info("Retrieving documents for student: {}", studentId);
        return documentRepository.findByStudentID(studentId);
    }

    public StudentDocument getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
    }

    public StudentDocument updateDocumentStatus(Long id, String status) {
        log.info("Changing status of document {} to {}", id, status);
        return documentRepository.findById(id).map(doc -> {
            doc.setVerificationStatus(status);
            return documentRepository.save(doc);
        }).orElseThrow(() -> new ResourceNotFoundException("Document status update failed. ID: " + id));
    }

    public void deleteDocument(Long id) {
        log.warn("Deleting document with ID: {}", id);
        documentRepository.deleteById(id);
    }
}