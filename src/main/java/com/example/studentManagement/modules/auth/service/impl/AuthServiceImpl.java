package com.example.studentManagement.modules.auth.service.impl;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.studentManagement.common.constant.CodePrefix;
import com.example.studentManagement.common.exception.BusinessException;
import com.example.studentManagement.common.exception.ErrorCode;
import com.example.studentManagement.modules.auth.dto.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.AuthRegisterResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.auth.mapper.UserMapper;
import com.example.studentManagement.modules.auth.repository.UserRepository;
import com.example.studentManagement.modules.auth.service.AuthService;
import com.example.studentManagement.modules.role.entity.RoleEntity;
import com.example.studentManagement.modules.role.enums.RoleType;
import com.example.studentManagement.modules.role.repository.RoleRepository;
import com.example.studentManagement.modules.student.entity.StudentEntity;
import com.example.studentManagement.modules.student.repository.StudentRepository;
import com.example.studentManagement.modules.teacher.entity.TeacherEntity;
import com.example.studentManagement.modules.teacher.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final UserMapper userMapper;

    @Override
    public AuthRegisterResponse register(AuthRegisterRequest request) {
        if (request.getRole() == RoleType.ADMIN) {
            throw new BusinessException(ErrorCode.ROLE_ASSIGN_NOT_ALLOWED.name(), "ADMIN is not allowed");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        RoleEntity role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND.name(), "Role not found"));
                
                userEntity.setRoleEntities(Set.of(role));
                
                userEntity.setFullName(request.getFullName());
                userEntity.setGender(request.getGender());
                userEntity.setDateOfBirth(request.getDateOfBirth());
                userRepository.save(userEntity);
        if (role.getName() == RoleType.STUDENT) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setUserEntity(userEntity);
            studentRepository.save(studentEntity);
            String code = CodePrefix.STUDENT.getPrefix() + String.format("%06d", studentEntity.getId());
            studentEntity.setStudentCode(code);
            studentRepository.save(studentEntity);
        }

        if (role.getName() == RoleType.TEACHER) {
            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setUserEntity(userEntity);
            teacherRepository.save(teacherEntity);
            String code = CodePrefix.TEACHER.getPrefix() + String.format("%06d", teacherEntity.getId());
            teacherEntity.setTeacherCode(code);
            teacherRepository.save(teacherEntity);
        }
        return userMapper.toResponse(userEntity);
    }

}
