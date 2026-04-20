package com.example.studentManagement.modules.auth.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.studentManagement.modules.auth.dto.request.AuthLoginRequest;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.role.enums.RoleType;

public class AuthControllerValidationTest extends BaseAuthControllerTest {
    
    @Test
    void login_shouldReturnBadRequest_whenUsernameIsBlank() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        request.setUsername("");

        mockMvc.perform(post(loginUri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_shouldReturnBadRequest_whenUsernameTooShort() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        request.setUsername("1"); // Too short

        mockMvc.perform(post(loginUri)
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isBadRequest());           
    }

    @Test
    void login_shouldReturnBadRequest_whenUsernameTooLong() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        request.setUsername("a".repeat(51)); // Too long

        mockMvc.perform(post(loginUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test 
    void login_shouldReturnBadRequest_whenPasswordIsBlank() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        request.setPassword(""); // Blank

        mockMvc.perform(post(loginUri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
    }

    @Test 
    void login_shouldReturnBadRequest_whenPasswordTooShort() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        request.setPassword("1"); // Too short
        
        mockMvc.perform(post(loginUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void login_shouldReturnBadRequest_whenPasswordTooLong() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        request.setPassword("1".repeat(51)); // Too long
        
        mockMvc.perform(post(loginUri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenUsernameIsBlank() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setUsername("");

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenUsernameTooShort() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setUsername("12"); // Too short

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenUsernameTooLong() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setUsername("a".repeat(51)); // Too long

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenPasswordIsBlank() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setPassword("");

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenPasswordTooShort() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setPassword("12"); // Too short

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenPasswordTooLong() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setPassword("a".repeat(51)); // Too long

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenFullNameIsBlank() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setFullName("");

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenFullNameTooShort() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setFullName("12"); // Too short

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenFullNameTooLong() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setFullName("a".repeat(101)); // Too long

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenRoleIsNull() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setRole(null);

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenGenderIsNull() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setGender(null);

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenDateOfBirthIsNull() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setDateOfBirth(null);

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenRoleIsInvalid() throws Exception {
        String invalidJson = """
            {
                "username": "validUser",
                "password": "validPass123",
                "fullName": "Valid Full Name",
                "role": "INVALID_ROLE",
                "gender": "MALE",
                "dateOfBirth": "2000-01-01"
            }
        """;

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenGenderIsInvalid() throws Exception {
        String invalidJson = """
            {
                "username": "validUser",
                "password": "validPass123",
                "fullName": "Valid Full Name",
                "role": "STUDENT",
                "gender": "INVALID_GENDER",
                "dateOfBirth": "2000-01-01"
            }
        """;

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnBadRequest_whenDateOfBirthIsInvalid() throws Exception {
        String invalidJson = """
            {
                "username": "validUser",
                "password": "validPass123",
                "fullName": "Valid Full Name",
                "role": "STUDENT",
                "gender": "MALE",
                "dateOfBirth": "AAAA-BB-CC"
            }
        """;

        mockMvc.perform(post(registerUri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidJson))
            .andExpect(status().isBadRequest());
    }
}
