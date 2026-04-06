package com.cts.edulink.student_service.controller;

import com.cts.edulink.student_service.entity.StudentDocument;
import com.cts.edulink.student_service.service.StudentDocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students/documents")
public class StudentDocumentController {

    @Autowired
    private StudentDocumentService documentService;

    @PostMapping
    public ResponseEntity<StudentDocument> upload(@Valid @RequestBody StudentDocument doc) {
        return ResponseEntity.status(201).body(documentService.uploadDocument(doc));
    }

    @GetMapping("/student/{studentId}")
    public List<StudentDocument> getByStudent(@PathVariable Long studentId) {
        return documentService.getDocumentsByStudent(studentId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDocument> getById(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    @PutMapping("/{id}/verify")
    public ResponseEntity<StudentDocument> verify(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(documentService.updateDocumentStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok("Document deleted successfully");
    }
}