package com.example.studentManagement.modules.auth.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.studentManagement.modules.auth.dto.request.AuthLoginRequest;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.auth.service.AuthService;
import com.example.studentManagement.modules.role.enums.RoleType;
import com.example.studentManagement.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AuthController.class)
public abstract class BaseAuthControllerTest {
    
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockitoBean
    protected AuthService authService;

    @MockitoBean
    protected JwtUtil jwtUtil;

    protected String loginUri = "/api/v1/auth/login";
    protected String registerUri = "/api/v1/auth/register";

    protected AuthLoginRequest createValidLoginRequest() {
        return AuthLoginRequest.builder()
            .username("12345")
            .password("12345678")
            .build();
    }

    protected AuthRegisterRequest createValidRegisterRequest() {
        return AuthRegisterRequest.builder()
            .username("validUser")
            .password("validPass123")
            .fullName("Valid Full Name")
            .role(RoleType.STUDENT)
            .gender(GenderType.MALE)
            .dateOfBirth(LocalDate.of(2000, 1, 1))
            .build();
    }
}
