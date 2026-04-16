package com.example.studentManagement.modules.auth.mapper;

import org.mapstruct.Mapper;

import com.example.studentManagement.modules.auth.dto.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.AuthRegisterResponse;
import com.example.studentManagement.modules.auth.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AuthRegisterResponse toResponse(UserEntity userEntity);
    UserEntity toEntity(AuthRegisterRequest request);
}


// @Component
// public class UserMapperImpl implements UserMapper {

//     @Override
//     public UserResponse toResponse(UserEntity userEntity) {
//         if (userEntity == null) {
//             return null;
//         }

//         UserResponse userResponse = new UserResponse();

//         userResponse.setUsername(userEntity.getUsername());
//         userResponse.setFullName(userEntity.getFullName());
//         userResponse.setRole(userEntity.getRole());
//         userResponse.setGender(userEntity.getGender());
//         userResponse.setActive(userEntity.isActive());
//         userResponse.setDateOfBirth(userEntity.getDateOfBirth());

//         return userResponse;
//     }
// }