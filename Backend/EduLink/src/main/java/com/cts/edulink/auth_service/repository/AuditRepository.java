package com.cts.edulink.auth_service.repository;

import com.cts.edulink.auth_service.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {
}