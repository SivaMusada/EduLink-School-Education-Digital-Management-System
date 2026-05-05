package com.cts.edulink.exam_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examID;

    @NotNull(message = "Course ID is required")
    private Long courseID;

    @NotBlank(message = "Exam type is required (Midterm, Final, etc.)")
    private String type;

    @FutureOrPresent(message = "Exam date cannot be in the past")
    private LocalDate date;

    @Pattern(regexp = "Scheduled|Completed|Cancelled", message = "Status must be Scheduled, Completed, or Cancelled")
    private String status = "Scheduled";
}