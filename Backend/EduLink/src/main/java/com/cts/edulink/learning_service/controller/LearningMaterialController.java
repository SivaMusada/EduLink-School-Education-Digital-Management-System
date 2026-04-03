package com.cts.edulink.learning_service.controller;

import com.cts.edulink.learning_service.entity.Assignment;
import com.cts.edulink.learning_service.entity.LearningMaterial;
import com.cts.edulink.learning_service.service.ILearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/learning")
public class LearningMaterialController {

    @Autowired
    private ILearningService learningService;

    @PostMapping("/upload")
    public ResponseEntity<LearningMaterial> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("courseId") Long courseId,
            @RequestParam("title") String title) {
        return ResponseEntity.ok(learningService.uploadMaterial(file, courseId, title));
    }

    @GetMapping("/materials/{courseId}")
    public List<LearningMaterial> getMaterials(@PathVariable Long courseId) {
        return learningService.getMaterialsByCourse(courseId);
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<LearningMaterial> trackUsage(@PathVariable Long id) {
        LearningMaterial updated = learningService.incrementMaterialUsage(id);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/assignments/submit")
    public ResponseEntity<Assignment> submitAssignment(@RequestBody Assignment assignment) {
        return ResponseEntity.ok(learningService.createAssignment(assignment));
    }

    @GetMapping("/assignments/student/{studentId}")
    public ResponseEntity<List<Assignment>> getStudentAssignments(@PathVariable Long studentId) {
        return ResponseEntity.ok(learningService.getStudentAssignments(studentId));
    }
}