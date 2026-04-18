package com.example.studentManagement.modules.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.studentManagement.modules.auth.dto.response.AuthLoginStudentResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthLoginTeacherResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterStudentResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterTeacherResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.clazz.dto.response.ClassSummary;
import com.example.studentManagement.modules.clazz.entity.ClassEntity;
import com.example.studentManagement.modules.department.dto.response.DepartmentSummary;
import com.example.studentManagement.modules.department.entity.DepartmentEntity;
import com.example.studentManagement.modules.role.entity.RoleEntity;
import com.example.studentManagement.modules.role.enums.RoleType;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", source = "roleEntities")
    AuthRegisterResponse toResponse(UserEntity userEntity);

    @Mapping(target = "roles", source = "roleEntities")
    @Mapping(target = "studentCode", source = "studentEntity.studentCode")
    @Mapping(target = "status", source = "studentEntity.status")
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    AuthLoginStudentResponse toLoginStudentResponse(UserEntity userEntity);

    @Mapping(target = "roles", source = "roleEntities")
    @Mapping(target = "teacherCode", source = "teacherEntity.teacherCode")
    @Mapping(target = "title", source = "teacherEntity.title")
    @Mapping(target = "department", source = "teacherEntity.departmentEntity")
    @Mapping(target = "clazz", source = "teacherEntity.clazzEntity")
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    AuthLoginTeacherResponse toLoginTeacherResponse(UserEntity userEntity);

    @Mapping(target = "studentCode", source = "studentEntity.studentCode")
    @Mapping(target = "status", source = "studentEntity.status")
    @Mapping(target = "roles", source = "roleEntities")
    AuthRegisterStudentResponse toAuthRegisterStudentResponse(UserEntity userEntity);

    @Mapping(target = "roles", source = "roleEntities")
    @Mapping(target = "title", source = "teacherEntity.title")
    @Mapping(target = "teacherCode", source = "teacherEntity.teacherCode")
    AuthRegisterTeacherResponse toAuthRegisterTeacherResponse(UserEntity userEntity);

    // phụ trợ
    DepartmentSummary toDepartmentSummary(DepartmentEntity departmentEntity);
    ClassSummary toClassSummary(ClassEntity classEntity);
    default RoleType toRoleType(RoleEntity role) {
        return role.getName();
    }

}

// @Component
// public class UserMapperImpl implements UserMapper {

// @Override
// public UserResponse toResponse(UserEntity userEntity) {
// if (userEntity == null) {
// return null;
// }

// UserResponse userResponse = new UserResponse();

// userResponse.setUsername(userEntity.getUsername());
// userResponse.setFullName(userEntity.getFullName());
// userResponse.setRole(userEntity.getRole());
// userResponse.setGender(userEntity.getGender());
// userResponse.setActive(userEntity.isActive());
// userResponse.setDateOfBirth(userEntity.getDateOfBirth());

// return userResponse;
// }
// }