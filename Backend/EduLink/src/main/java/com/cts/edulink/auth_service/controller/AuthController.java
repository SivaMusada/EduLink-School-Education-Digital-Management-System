package com.cts.edulink.auth_service.controller;

import com.cts.edulink.auth_service.model.AuditLog;
import com.cts.edulink.auth_service.model.LoginRequest;
import com.cts.edulink.auth_service.model.LoginResponse;
import com.cts.edulink.auth_service.model.User;
import com.cts.edulink.auth_service.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // --- POST METHODS ---

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(authService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login API called for email: {}", loginRequest.getEmail());

        String token = authService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        if (token != null) {
            return ResponseEntity.ok(
                    new LoginResponse("Login successful", token)
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Email or Password");
    }

    // --- USER GET METHODS ---

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return authService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- AUDIT GET METHODS ---

    @GetMapping("/audit-logs")
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        return ResponseEntity.ok(authService.getAllAuditLogs());
    }

    @GetMapping("/audit-logs/{id}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable Long id) {
        return authService.getAuditLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}