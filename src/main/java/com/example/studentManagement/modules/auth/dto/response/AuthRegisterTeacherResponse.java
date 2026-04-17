package com.example.studentManagement.modules.auth.dto.response;

import com.example.studentManagement.modules.teacher.enums.TitleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthRegisterTeacherResponse extends AuthRegisterResponse{
    private TitleType title;
    private String teacherCode;
}
