package com.example.studentManagement.modules.auth.service.login;

import org.springframework.stereotype.Service;

import com.example.studentManagement.modules.auth.dto.response.AuthLoginTeacherResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.auth.mapper.UserMapper;
import com.example.studentManagement.modules.role.enums.RoleType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherLoginStrategy implements LoginStrategy{
    private final UserMapper userMapper;

    @Override
    public RoleType getRole() {
        return RoleType.TEACHER;
    }

    @Override
    public AuthLoginTeacherResponse login(UserEntity userEntity, String accessToken, String refreshToken) {
        return userMapper.toLoginTeacherResponse(userEntity)
            .toBuilder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    
}
