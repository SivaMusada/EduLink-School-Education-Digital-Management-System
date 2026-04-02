package com.cts.edulink.exam_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examID;

    private Long courseID; // Links to Academic Course
    private String type;   // Midterm, Final, Quiz
    private LocalDate date;
    private String status; // Scheduled, Completed, Cancelled
}