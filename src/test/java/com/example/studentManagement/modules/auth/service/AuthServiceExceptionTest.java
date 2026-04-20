package com.example.studentManagement.modules.auth.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.studentManagement.common.exception.BusinessException;
import com.example.studentManagement.common.exception.ErrorCode;
import com.example.studentManagement.modules.auth.dto.request.AuthLoginRequest;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.role.enums.RoleType;

public class AuthServiceExceptionTest extends BaseAuthServiceTest {

    @Test
    void login_shouldThrowException_whenUserNotFound() {
        AuthLoginRequest request = AuthLoginRequest.builder()
                .username("unknown")
                .password("password")
                .build();

        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> authService.login(request));
        assertEquals(ErrorCode.USER_NOT_FOUND.name(), exception.getCode());
        verify(userRepository, times(1)).findByUsername("unknown");
    }

    @Test
    void login_shouldThrowException_whenPasswordIsIncorrect() {
        AuthLoginRequest request = AuthLoginRequest.builder()
                .username("user")
                .password("wrongPassword")
                .build();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user");
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        BusinessException exception = assertThrows(BusinessException.class, () -> authService.login(request));
        assertEquals(ErrorCode.PASSWORD_IS_INCORRECT.name(), exception.getCode());
        verify(userRepository, times(1)).findByUsername("user");
        verify(passwordEncoder, times(1)).matches("wrongPassword", "encodedPassword");
    }

    @Test
    void login_shouldThrowException_whenAccountNotActivated() {
        AuthLoginRequest request = AuthLoginRequest.builder()
                .username("user")
                .password("password")
                .build();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user");
        userEntity.setPassword("encodedPassword");
        userEntity.setIsActive(false);

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> authService.login(request));
        assertEquals(ErrorCode.ACCOUNT_NOT_ACTIVATED.name(), exception.getCode());
        verify(userRepository, times(1)).findByUsername("user");
        verify(passwordEncoder, times(1)).matches("password", "encodedPassword");
    }

    @Test
    void register_shouldThrowException_whenRoleIsAdmin() {
        AuthRegisterRequest request = AuthRegisterRequest.builder()
                .role(RoleType.ADMIN)
                .build();

        BusinessException exception = assertThrows(BusinessException.class, () -> authService.register(request));
        assertEquals(ErrorCode.ROLE_ASSIGN_NOT_ALLOWED.name(), exception.getCode());
    }

    @Test
    void register_shouldThrowException_whenRoleNotFound() {
        AuthRegisterRequest request = AuthRegisterRequest.builder()
                .role(RoleType.STUDENT)
                .username("user")
                .password("password")
                .build();

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName(RoleType.STUDENT)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> authService.register(request));
        assertEquals(ErrorCode.ROLE_NOT_FOUND.name(), exception.getCode());
        verify(passwordEncoder, times(1)).encode("password");
        verify(roleRepository, times(1)).findByName(RoleType.STUDENT);
    }
}
