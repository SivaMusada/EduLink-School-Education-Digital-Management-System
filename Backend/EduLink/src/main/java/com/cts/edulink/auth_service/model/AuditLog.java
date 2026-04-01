package com.cts.edulink.auth_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AuditLog")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditID;

    private Long userID;
    private String action;   // e.g., "USER_REGISTER", "USER_LOGIN_SUCCESS", "USER_LOGIN_FAILED"
    private String resource; // e.g., "AuthService"

    private LocalDateTime timestamp = LocalDateTime.now();
}