package com.cts.edulink.auth_service.service;

import com.cts.edulink.auth_service.model.AuditLog;
import com.cts.edulink.auth_service.model.User;
import com.cts.edulink.auth_service.repository.AuditRepository;
import com.cts.edulink.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditRepository auditRepository;

    // --- REGISTRATION & LOGIN ---

    public User registerUser(User user) {
        User savedUser = userRepository.save(user);
        saveLog(savedUser.getUserID(), "USER_REGISTER", "AuthModule");
        return savedUser;
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getPassword().equals(password)) {
            saveLog(user.getUserID(), "USER_LOGIN_SUCCESS", "AuthModule");
            return user;
        } else {
            if (user != null) {
                saveLog(user.getUserID(), "USER_LOGIN_FAILED", "AuthModule");
            }
            return null;
        }
    }

    // --- USER RETRIEVAL ---

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // --- AUDIT RETRIEVAL ---

    public List<AuditLog> getAllAuditLogs() {
        return auditRepository.findAll();
    }

    public Optional<AuditLog> getAuditLogById(Long id) {
        return auditRepository.findById(id);
    }

    // --- INTERNAL LOGGING HELPER ---

    private void saveLog(Long userId, String action, String resource) {
        AuditLog log = AuditLog.builder()
                .userID(userId)
                .action(action)
                .resource(resource)
                .timestamp(LocalDateTime.now())
                .build();
        auditRepository.save(log);
    }
}