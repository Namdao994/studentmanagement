package com.example.studentManagement.common.exception;

public enum ErrorCode {
    NOT_FOUND,
    VALIDATION_ERROR,
    INTERNAL_ERROR,
    BAD_REQUEST,
    ALREADY_EXISTS,

    //Role
    ROLE_ASSIGN_NOT_ALLOWED,
    ROLE_NOT_FOUND,
    ROLE_INVALID,

    //User
    USER_NOT_FOUND,
    PASSWORD_IS_INCORRECT,
    ACCOUNT_NOT_ACTIVATED,
    ROLE_NOT_SUPPORTED
}
