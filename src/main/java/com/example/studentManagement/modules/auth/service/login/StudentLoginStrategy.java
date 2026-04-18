package com.example.studentManagement.modules.auth.service.login;

import org.springframework.stereotype.Service;

import com.example.studentManagement.modules.auth.dto.response.AuthLoginStudentResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.auth.mapper.UserMapper;
import com.example.studentManagement.modules.role.enums.RoleType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentLoginStrategy implements LoginStrategy {

    private final UserMapper userMapper;

    @Override
    public RoleType getRole() {
        return RoleType.STUDENT;
    }

    @Override
    public AuthLoginStudentResponse login(UserEntity userEntity, String accessToken, String refreshToken) {
        return userMapper.toLoginStudentResponse(userEntity)
            .toBuilder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
    
}
