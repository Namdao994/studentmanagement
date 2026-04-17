package com.example.studentManagement.modules.auth.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.role.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AuthLoginResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String fullName;
    private Set<RoleType> roles;
    private GenderType gender;
    private Boolean isActive;
    private LocalDate dateOfBirth;
}
