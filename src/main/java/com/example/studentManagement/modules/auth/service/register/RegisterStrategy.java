package com.example.studentManagement.modules.auth.service.register;

import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.role.enums.RoleType;


public interface RegisterStrategy {
    RoleType getRole();
    AuthRegisterResponse register(UserEntity userEntity, AuthRegisterRequest request);
}