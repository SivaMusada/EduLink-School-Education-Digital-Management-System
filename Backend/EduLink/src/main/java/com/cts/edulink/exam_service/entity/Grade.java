package com.cts.edulink.exam_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeID;

    private Long examID;    // Links to Exam
    private Long studentID; // Links to Student
    private Double score;
    private String grade;   // A, B, C, F
    private String status;  // Published, Pending
}