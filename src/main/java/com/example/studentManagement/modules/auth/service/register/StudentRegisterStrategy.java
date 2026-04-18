package com.example.studentManagement.modules.auth.service.register;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.studentManagement.common.constant.CodePrefix;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterStudentResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.auth.mapper.UserMapper;
import com.example.studentManagement.modules.role.enums.RoleType;
import com.example.studentManagement.modules.student.entity.StudentEntity;
import com.example.studentManagement.modules.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentRegisterStrategy implements RegisterStrategy {
    private final StudentRepository studentRepository;
    private final UserMapper userMapper;
    @Override
    public RoleType getRole() {
        return RoleType.STUDENT;
    }

    @Override
    public AuthRegisterStudentResponse register(UserEntity userEntity, AuthRegisterRequest request) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUserEntity(userEntity);
        String code = CodePrefix.STUDENT.getPrefix() + UUID.randomUUID().toString().substring(0, 8);
        studentEntity.setStudentCode(code);
        studentRepository.save(studentEntity);
        userEntity.setStudentEntity(studentEntity);
        return userMapper.toAuthRegisterStudentResponse(userEntity);
    }
}
