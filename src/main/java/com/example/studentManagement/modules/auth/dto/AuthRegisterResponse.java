package com.example.studentManagement.modules.auth.dto;

import java.time.LocalDate;

import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.role.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegisterResponse {
    
    private String username;
    private String fullName;
    private RoleType role;
    private GenderType gender;
    private boolean isActive;
    private LocalDate dateOfBirth;
}
