package com.example.studentManagement.common.response;

import java.time.Instant;

public record ApiResponse<T>(boolean success, T data, ApiError error, ApiMeta meta) {

    public static <T> ApiResponse<T> ok (T data) {
        return new ApiResponse<T>(true, data, null, new ApiMeta(Instant.now().toString()));
    }

    public static <T> ApiResponse<T> fail (ApiError error) {
        return new ApiResponse<T>(false, null, error, new ApiMeta(Instant.now().toString()));
    }
    
}
