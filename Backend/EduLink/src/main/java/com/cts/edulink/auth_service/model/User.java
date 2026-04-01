package com.cts.edulink.auth_service.model;

import com.fasterxml.jackson.annotation.JsonProperty; // Import this
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String name;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // This hides it from the output
    private String password;

    private String role;
    private String phone;

    private String status = "Active"; // Set a default value here
}