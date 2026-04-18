package com.example.studentManagement.modules.auth.service.register;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.studentManagement.common.constant.CodePrefix;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterTeacherResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.auth.mapper.UserMapper;
import com.example.studentManagement.modules.role.enums.RoleType;
import com.example.studentManagement.modules.teacher.entity.TeacherEntity;
import com.example.studentManagement.modules.teacher.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherRegisterStrategy implements RegisterStrategy{

    private final TeacherRepository teacherRepository;
    private final UserMapper userMapper;

    @Override
    public RoleType getRole() {
        return RoleType.TEACHER;
    }

    @Override
    public AuthRegisterTeacherResponse register(UserEntity userEntity, AuthRegisterRequest request) {
        TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setUserEntity(userEntity);
            String code = CodePrefix.TEACHER.getPrefix() + UUID.randomUUID().toString().substring(0, 8);
            teacherEntity.setTeacherCode(code);
            teacherRepository.save(teacherEntity);
            userEntity.setTeacherEntity(teacherEntity);
            return userMapper.toAuthRegisterTeacherResponse(userEntity);
    }
    
}
