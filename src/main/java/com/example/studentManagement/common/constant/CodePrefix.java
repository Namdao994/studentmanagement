package com.example.studentManagement.common.constant;

import lombok.Getter;

@Getter
public enum CodePrefix {

    STUDENT("STU"),
    TEACHER("TCH");

    private final String prefix;

    CodePrefix(String prefix) {
        this.prefix = prefix;
    }
}