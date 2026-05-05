package com.cts.edulink.auth_service.service;

import com.cts.edulink.auth_service.model.AuditLog;
import com.cts.edulink.auth_service.model.User;
import com.cts.edulink.auth_service.repository.AuditRepository;
import com.cts.edulink.auth_service.repository.UserRepository;
import com.cts.edulink.auth_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // --- REGISTRATION & LOGIN ---

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        log.info("User registered: {}", savedUser.getEmail());
        saveLog(savedUser.getUserID(), "USER_REGISTER", "AuthModule");
        return savedUser;
           }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            log.info("Login success for user: {}", email);
            saveLog(user.getUserID(), "USER_LOGIN_SUCCESS", "AuthModule");
            return jwtUtil.generateToken(email);
        }else {
            log.error("Login failed for user: {}", email);
            if (user != null) {
                saveLog(user.getUserID(), "USER_LOGIN_FAILED", "AuthModule");
            }
            return null;
        }
    }
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName()); // Assuming User has a name field
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            // Do not update password here unless you handle encryption

            User updatedUser = userRepository.save(user);
            log.info("User updated: {}", updatedUser.getEmail());
            saveLog(updatedUser.getUserID(), "USER_UPDATE", "AuthModule");
            return updatedUser;
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }
    // --- USER RETRIEVAL ---

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsersByRole(String role) {
        log.info("Fetching users with role:{}", role);
        return userRepository.findByRole(role);
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
