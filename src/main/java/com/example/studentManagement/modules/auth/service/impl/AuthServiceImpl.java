package com.example.studentManagement.modules.auth.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studentManagement.common.constant.CodePrefix;
import com.example.studentManagement.common.exception.BusinessException;
import com.example.studentManagement.common.exception.ErrorCode;
import com.example.studentManagement.modules.auth.dto.request.AuthLoginRequest;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.response.AuthLoginResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterResponse;
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
import com.example.studentManagement.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

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
            String code = CodePrefix.STUDENT.getPrefix() + UUID.randomUUID().toString().substring(0, 8);
            studentEntity.setStudentCode(code);
            studentRepository.save(studentEntity);
            userEntity.setStudentEntity(studentEntity);
            return userMapper.toAuthRegisterStudentResponse(userEntity);
        }
        if (role.getName() == RoleType.TEACHER) {
            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setUserEntity(userEntity);
            String code = CodePrefix.TEACHER.getPrefix() + UUID.randomUUID().toString().substring(0, 8);
            teacherEntity.setTeacherCode(code);
            teacherRepository.save(teacherEntity);
            userEntity.setTeacherEntity(teacherEntity);
            return userMapper.toAuthRegisterTeacherResponse(userEntity);
        }
        throw new BusinessException(ErrorCode.ROLE_NOT_SUPPORTED.name(), "Role is not supported for login response");
    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {

        UserEntity userEntity = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND.name(), "User not found"));

        boolean isValidPassword = passwordEncoder.matches(request.getPassword(), userEntity.getPassword());

        if (!isValidPassword) {
            throw new BusinessException(ErrorCode.PASSWORD_IS_INCORRECT.name(), "Password is incorrect");
        }

        boolean isActive = userEntity.getIsActive();
        if (!isActive) {
            throw new BusinessException(ErrorCode.ACCOUNT_NOT_ACTIVATED.name(), "Account is not activated");
        }

        List<RoleType> roles = userEntity.getRoleEntities().stream().map(RoleEntity::getName).toList();
        String accessToken = jwtUtil.generateAccessToken(userEntity.getUsername(), roles);
        String refreshToken = jwtUtil.generateRefreshToken(userEntity.getUsername(), roles);

        if (roles.contains(RoleType.STUDENT)) {
             return userMapper.toLoginStudentResponse(userEntity)
            .toBuilder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
        }
        if (roles.contains(RoleType.TEACHER)) {
            return userMapper.toLoginTeacherResponse(userEntity)
            .toBuilder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
        }
        throw new BusinessException(ErrorCode.ROLE_NOT_SUPPORTED.name(), "Role is not supported for login response");
    }

}
