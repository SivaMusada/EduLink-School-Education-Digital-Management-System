package com.cts.edulink.learning_service.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "learning_materials")
@Data
public class LearningMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    private Long courseId;
    private String title;
    private String fileUri;
    private LocalDateTime uploadedDate;
    private String status;
    private Integer viewCount = 0;
}