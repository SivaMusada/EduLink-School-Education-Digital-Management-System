package com.cts.edulink.auth_service.model;

import com.fasterxml.jackson.annotation.JsonProperty; // Import this
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // This hides it from the output
    private String password;

    @NotBlank(message = "Role is required")
    private String role;
    @Pattern(regexp="^[6-9]\\d{9}$",message = "Phone must be 10 digits")
    private String phone;

    private String status = "Active"; // Set a default value here
}