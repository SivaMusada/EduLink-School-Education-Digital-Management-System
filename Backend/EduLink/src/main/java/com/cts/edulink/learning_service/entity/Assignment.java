package com.cts.edulink.learning_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    private Long courseId;
    private Long studentId;
    private String title;
    private LocalDateTime submissionDate;
    private String status;
}