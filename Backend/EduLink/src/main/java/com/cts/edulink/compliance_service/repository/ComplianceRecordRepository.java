package com.cts.edulink.compliance_service.repository;

import com.cts.edulink.compliance_service.entity.ComplianceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplianceRecordRepository extends JpaRepository<ComplianceRecord, Long> {
}