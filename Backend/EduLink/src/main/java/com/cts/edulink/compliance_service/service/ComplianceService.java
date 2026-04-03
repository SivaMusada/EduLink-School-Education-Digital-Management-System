package com.cts.edulink.compliance_service.service;

import com.cts.edulink.compliance_service.entity.Audit;
import com.cts.edulink.compliance_service.entity.ComplianceRecord;
import com.cts.edulink.compliance_service.repository.ComplianceAuditRepository;
import com.cts.edulink.compliance_service.repository.ComplianceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComplianceService {

    @Autowired
    private ComplianceRecordRepository complianceRepository;

    @Autowired
    private ComplianceAuditRepository auditRepository;

    public ComplianceRecord createRecord(ComplianceRecord record) {
        if (record.getDate() == null) {
            record.setDate(LocalDate.now());
        }
        return complianceRepository.save(record);
    }

    public List<ComplianceRecord> getAllRecords() {
        return complianceRepository.findAll();
    }

    public Optional<ComplianceRecord> getRecordById(Long id) {
        return complianceRepository.findById(id);
    }

    public void deleteRecord(Long id) {
        complianceRepository.deleteById(id);
    }


    public Audit createAudit(Audit audit) {
        if (audit.getDate() == null) {
            audit.setDate(LocalDate.now());
        }
        return auditRepository.save(audit);
    }

    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }

    public Optional<Audit> getAuditById(Long id) {
        return auditRepository.findById(id);
    }

    public void deleteAudit(Long id) {
        auditRepository.deleteById(id);
    }
}