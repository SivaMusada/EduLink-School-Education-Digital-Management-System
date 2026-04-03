package com.cts.edulink.learning_service.service;

import jakarta.transaction.Transactional;
import com.cts.edulink.learning_service.entity.LearningMaterial;
import com.cts.edulink.learning_service.entity.Assignment;
import com.cts.edulink.learning_service.repository.LearningMaterialRepository;
import com.cts.edulink.learning_service.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LearningServiceImpl implements ILearningService {

    @Autowired
    private LearningMaterialRepository materialRepo;

    @Autowired
    private AssignmentRepository assignmentRepo;

    @Override
    public LearningMaterial uploadMaterial(MultipartFile file, Long courseId, String title) {
        try {
            String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

            File dir = new File(uploadDirectory);
            if (!dir.exists()) dir.mkdirs();

            Path filePath = Paths.get(uploadDirectory + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            LearningMaterial material = new LearningMaterial();
            material.setCourseId(courseId);
            material.setTitle(title);
            material.setFileUri("/uploads/" + file.getOriginalFilename());
            material.setUploadedDate(LocalDateTime.now());
            material.setStatus("ACTIVE");

            return materialRepo.save(material);

        } catch (IOException e) {
            throw new RuntimeException("Could not save file: " + e.getMessage());
        }
    }

    @Override
    public List<LearningMaterial> getMaterialsByCourse(Long courseId) {
        return materialRepo.findByCourseId(courseId);
    }

    @Override
    public Assignment createAssignment(Assignment assignment) {
        assignment.setStatus("PENDING");
        return assignmentRepo.save(assignment);
    }

    @Override
    public List<Assignment> getStudentAssignments(Long studentId) {
        return assignmentRepo.findByStudentId(studentId);
    }


    @Transactional
    @Override
    public LearningMaterial incrementMaterialUsage(Long id) {
        LearningMaterial material = materialRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Material ID " + id + " not found."));

        int currentViews = (material.getViewCount() == null) ? 0 : material.getViewCount();
        material.setViewCount(currentViews + 1);

        return materialRepo.save(material);
    }

    @Override
    public LearningMaterial getMaterialById(Long id) {
        return null;
    }
}