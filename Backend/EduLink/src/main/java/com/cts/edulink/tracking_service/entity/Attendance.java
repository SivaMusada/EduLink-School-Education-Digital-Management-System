package com.cts.edulink.tracking_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceID;

    private Long studentID;
    private Long classID;
    private LocalDate date;
    private String status;
}