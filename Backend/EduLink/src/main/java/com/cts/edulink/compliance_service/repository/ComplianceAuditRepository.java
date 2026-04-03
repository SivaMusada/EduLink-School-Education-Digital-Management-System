package com.cts.edulink.compliance_service.repository;

import com.cts.edulink.compliance_service.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplianceAuditRepository extends JpaRepository<Audit, Long> {
}