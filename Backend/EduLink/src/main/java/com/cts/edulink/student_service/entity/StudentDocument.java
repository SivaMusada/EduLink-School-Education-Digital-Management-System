package com.cts.edulink.student_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class StudentDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentID;

    @NotNull(message = "Student ID is mandatory")
    private Long studentID;

    @NotBlank(message = "Document Type is mandatory")
    private String docType;

    @NotBlank(message = "File URI is mandatory")
    private String fileURI;

    private LocalDateTime uploadedDate;
    private String verificationStatus;

    @PrePersist
    protected void onUpload() {
        this.uploadedDate = LocalDateTime.now();
        if (this.verificationStatus == null) this.verificationStatus = "Pending";
    }
}