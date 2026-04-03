package com.cts.edulink.compliance_service.controller;

import com.cts.edulink.compliance_service.entity.Audit;
import com.cts.edulink.compliance_service.entity.ComplianceRecord;
import com.cts.edulink.compliance_service.service.ComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance")
@CrossOrigin(origins = "*")
public class ComplianceController {

    @Autowired
    private ComplianceService complianceService;

    @PostMapping("/records")
    public ResponseEntity<ComplianceRecord> createRecord(@RequestBody ComplianceRecord record) {
        return ResponseEntity.status(201).body(complianceService.createRecord(record));
    }

    @GetMapping("/records")
    public List<ComplianceRecord> getAllRecords() {
        return complianceService.getAllRecords();
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<ComplianceRecord> getRecordById(@PathVariable Long id) {
        return complianceService.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/records/{id}")
    public ResponseEntity<ComplianceRecord> updateRecord(@PathVariable Long id, @RequestBody ComplianceRecord recordDetails) {
        return complianceService.getRecordById(id).map(record -> {
            record.setResult(recordDetails.getResult());
            record.setNotes(recordDetails.getNotes());
            record.setType(recordDetails.getType());
            return ResponseEntity.ok(complianceService.createRecord(record));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        complianceService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/audits")
    public ResponseEntity<Audit> createAudit(@RequestBody Audit audit) {
        return ResponseEntity.status(201).body(complianceService.createAudit(audit));
    }

    @GetMapping("/audits")
    public List<Audit> getAllAudits() {
        return complianceService.getAllAudits();
    }

    @GetMapping("/audits/{id}")
    public ResponseEntity<Audit> getAuditById(@PathVariable Long id) {
        return complianceService.getAuditById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/audits/{id}")
    public ResponseEntity<Audit> updateAudit(@PathVariable Long id, @RequestBody Audit auditDetails) {
        return complianceService.getAuditById(id).map(audit -> {
            audit.setOfficerId(auditDetails.getOfficerId());
            audit.setScope(auditDetails.getScope());
            audit.setFindings(auditDetails.getFindings());
            audit.setDate(auditDetails.getDate());
            audit.setStatus(auditDetails.getStatus());
            return ResponseEntity.ok(complianceService.createAudit(audit));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/audits/{id}")
    public ResponseEntity<Void> deleteAudit(@PathVariable Long id) {
        complianceService.deleteAudit(id);
        return ResponseEntity.noContent().build();
    }
}