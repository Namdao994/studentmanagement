package com.example.studentManagement.modules.auth.controller;

import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.studentManagement.modules.auth.dto.request.AuthLoginRequest;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.response.AuthLoginStudentResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthLoginTeacherResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterStudentResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterTeacherResponse;
import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.role.enums.RoleType;
import com.example.studentManagement.modules.student.enums.StatusType;
import com.example.studentManagement.modules.teacher.enums.TitleType;

public class AuthControllerResponseTest extends BaseAuthControllerTest {

    private AuthLoginStudentResponse createValidLoginStudentResponse() {
        return AuthLoginStudentResponse.builder()
                .username("12345")
                .fullName("Full Name")
                .roles(Set.of(RoleType.STUDENT))
                .gender(GenderType.MALE)
                .isActive(false)
                .studentCode("STU1234")
                .status(StatusType.PENDING)
                .dateOfBirth(LocalDate.parse("2003-04-20"))
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();
    }

    private AuthLoginTeacherResponse createValidLoginTeacherResponse() {
        return AuthLoginTeacherResponse.builder()
                .username("12345")
                .fullName("Full Name")
                .roles(Set.of(RoleType.TEACHER))
                .gender(GenderType.MALE)
                .isActive(false)
                .teacherCode("TCH1234")
                .title(TitleType.ASSISTANT_LECTURER)
                .dateOfBirth(LocalDate.parse("2003-04-20"))
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();
    }

    @Test
    void login_shouldReturnOk_andInformation_whenValidRequestWithStudentRole() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        AuthLoginStudentResponse response = createValidLoginStudentResponse();

        when(authService.login(any(AuthLoginRequest.class))).thenReturn(response);

        mockMvc.perform(post(loginUri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.data.refreshToken").value("refreshToken"))
                .andExpect(jsonPath("$.data.username").value("12345"))
                .andExpect(jsonPath("$.data.fullName").value("Full Name"))
                .andExpect(jsonPath("$.data.roles", hasItem("STUDENT")))
                .andExpect(jsonPath("$.data.gender").value("MALE"))
                .andExpect(jsonPath("$.data.isActive").value(false))
                .andExpect(jsonPath("$.data.dateOfBirth").value("2003-04-20"))
                .andExpect(jsonPath("$.data.studentCode").value("STU1234"))
                .andExpect(jsonPath("$.data.status").value("PENDING"))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.meta.timestamp").exists());
    }

    @Test
    void login_shouldReturnOk_andInformation_whenValidRequestWithTeacherRole() throws Exception {
        AuthLoginRequest request = createValidLoginRequest();
        AuthLoginTeacherResponse response = createValidLoginTeacherResponse();

        when(authService.login(any(AuthLoginRequest.class))).thenReturn(response);

        mockMvc.perform(post(loginUri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.data.refreshToken").value("refreshToken"))
                .andExpect(jsonPath("$.data.username").value("12345"))
                .andExpect(jsonPath("$.data.fullName").value("Full Name"))
                .andExpect(jsonPath("$.data.roles", hasItem("TEACHER")))
                .andExpect(jsonPath("$.data.gender").value("MALE"))
                .andExpect(jsonPath("$.data.isActive").value(false))
                .andExpect(jsonPath("$.data.dateOfBirth").value("2003-04-20"))
                .andExpect(jsonPath("$.data.teacherCode").value("TCH1234"))
                .andExpect(jsonPath("$.data.title").value("ASSISTANT_LECTURER"))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.meta.timestamp").exists());
    }

    private AuthRegisterStudentResponse createValidRegisterStudentResponse() {
        return AuthRegisterStudentResponse.builder()
                .username("validUser")
                .fullName("Valid Full Name")
                .roles(Set.of(RoleType.STUDENT))
                .gender(GenderType.MALE)
                .isActive(true)
                .studentCode("STU5678")
                .status(StatusType.PENDING)
                .dateOfBirth(LocalDate.parse("2000-01-01"))
                .build();
    }

    private AuthRegisterTeacherResponse createValidRegisterTeacherResponse() {
        return AuthRegisterTeacherResponse.builder()
                .username("validUser")
                .fullName("Valid Full Name")
                .roles(Set.of(RoleType.TEACHER))
                .gender(GenderType.MALE)
                .isActive(true)
                .teacherCode("TCH5678")
                .title(TitleType.ASSISTANT_LECTURER)
                .dateOfBirth(LocalDate.parse("2000-01-01"))
                .build();
    }

    @Test
    void register_shouldReturnCreated_andInformation_whenValidRequestWithStudentRole() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        AuthRegisterStudentResponse response = createValidRegisterStudentResponse();

        when(authService.register(any(AuthRegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post(registerUri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username").value("validUser"))
                .andExpect(jsonPath("$.data.fullName").value("Valid Full Name"))
                .andExpect(jsonPath("$.data.roles", hasItem("STUDENT")))
                .andExpect(jsonPath("$.data.gender").value("MALE"))
                .andExpect(jsonPath("$.data.isActive").value(true))
                .andExpect(jsonPath("$.data.dateOfBirth").value("2000-01-01"))
                .andExpect(jsonPath("$.data.studentCode").value("STU5678"))
                .andExpect(jsonPath("$.data.status").value("PENDING"))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.meta.timestamp").exists());
    }

    @Test
    void register_shouldReturnCreated_andInformation_whenValidRequestWithTeacherRole() throws Exception {
        AuthRegisterRequest request = createValidRegisterRequest();
        request.setRole(RoleType.TEACHER);
        AuthRegisterTeacherResponse response = createValidRegisterTeacherResponse();

        when(authService.register(any(AuthRegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post(registerUri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username").value("validUser"))
                .andExpect(jsonPath("$.data.fullName").value("Valid Full Name"))
                .andExpect(jsonPath("$.data.roles", hasItem("TEACHER")))
                .andExpect(jsonPath("$.data.gender").value("MALE"))
                .andExpect(jsonPath("$.data.isActive").value(true))
                .andExpect(jsonPath("$.data.dateOfBirth").value("2000-01-01"))
                .andExpect(jsonPath("$.data.teacherCode").value("TCH5678"))
                .andExpect(jsonPath("$.data.title").value("ASSISTANT_LECTURER"))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.meta.timestamp").exists());
    }
}
