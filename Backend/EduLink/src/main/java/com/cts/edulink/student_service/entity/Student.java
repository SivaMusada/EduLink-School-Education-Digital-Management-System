package com.cts.edulink.student_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Date of Birth is mandatory")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dob;

    private String gender;
    private String address;

    @NotBlank(message = "Contact info is mandatory")
    private String contactInfo;

    private LocalDate enrollmentDate;
    private String status;

    @PrePersist
    protected void onCreate() {
        this.enrollmentDate = LocalDate.now();
        if (this.status == null) this.status = "Pending";
    }
}