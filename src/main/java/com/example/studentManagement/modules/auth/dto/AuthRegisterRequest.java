package com.example.studentManagement.modules.auth.dto;

import java.time.LocalDate;

import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.role.enums.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 50, message = "Password must be 3-50 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotNull(message = "Role is required")
    private RoleType role;

    @NotNull(message = "Gender is required")
    private GenderType gender;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;
}