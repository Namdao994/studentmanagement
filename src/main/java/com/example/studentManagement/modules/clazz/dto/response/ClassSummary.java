package com.example.studentManagement.modules.clazz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassSummary {
    private Long id;
    private String classCode;
    private String className;
}
