package com.example.studentManagement.modules.auth.dto.response;

import com.example.studentManagement.modules.clazz.dto.response.ClassSummary;
import com.example.studentManagement.modules.department.dto.response.DepartmentSummary;
import com.example.studentManagement.modules.teacher.enums.TitleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AuthLoginTeacherResponse extends AuthLoginResponse {
    private String teacherCode;
    private TitleType title;
    private DepartmentSummary department;
    private ClassSummary clazz;
}
