package com.example.studentManagement.modules.auth.service.login;

import com.example.studentManagement.modules.auth.dto.response.AuthLoginResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.role.enums.RoleType;

public interface LoginStrategy {
    public RoleType getRole();
    public AuthLoginResponse login(UserEntity userEntity, String accessToken, String refreshToken);
}
