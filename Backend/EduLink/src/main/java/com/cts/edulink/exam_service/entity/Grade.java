package com.cts.edulink.exam_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeID;

    @NotNull(message = "Exam ID is required")
    private Long examID;

    @NotNull(message = "Student ID is required")
    private Long studentID;

    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot exceed 100")
    private Double score;

    @NotBlank(message = "Grade letter is required")
    private String grade; // A, B, C, F

    private String status = "Pending"; // Published, Pending
}