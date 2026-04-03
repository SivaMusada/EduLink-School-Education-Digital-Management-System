package com.cts.edulink.learning_service.service;


import jakarta.transaction.Transactional;
import com.cts.edulink.learning_service.entity.LearningMaterial;
import com.cts.edulink.learning_service.entity.Assignment;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ILearningService {
    LearningMaterial uploadMaterial(MultipartFile file, Long courseId, String title);
    List<LearningMaterial> getMaterialsByCourse(Long courseId);
    Assignment createAssignment(Assignment assignment);
    List<Assignment> getStudentAssignments(Long studentId);

    @Transactional
    LearningMaterial incrementMaterialUsage(Long id);

    LearningMaterial getMaterialById(Long id);
}