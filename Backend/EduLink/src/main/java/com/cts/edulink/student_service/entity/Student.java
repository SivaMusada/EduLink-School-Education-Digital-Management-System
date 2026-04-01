package com.cts.edulink.student_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;

    private String name;
    private LocalDate dob;
    private String gender;
    private String address;
    private String contactInfo;
    private LocalDate enrollmentDate;
    private String status;

    @PrePersist
    protected void onCreate() {
        this.enrollmentDate = LocalDate.now();
        if (this.status == null) this.status = "Pending";
    }
}