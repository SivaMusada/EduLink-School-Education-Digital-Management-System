package com.cts.edulink.student_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class StudentDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentID;

    private Long studentID;
    private String docType;
    private String fileURI;
    private LocalDateTime uploadedDate;
    private String verificationStatus;

    @PrePersist
    protected void onUpload() {
        this.uploadedDate = LocalDateTime.now();
        if (this.verificationStatus == null) this.verificationStatus = "Pending";
    }
}