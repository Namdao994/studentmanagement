package com.example.studentManagement.modules.auth.dto.response;


import com.example.studentManagement.modules.student.enums.StatusType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuthRegisterStudentResponse extends AuthRegisterResponse{
    private String studentCode;
    private StatusType status;
}
