package com.example.studentManagement.modules.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentManagement.common.response.ApiResponse;

@RestController

public class HealthController {
    @GetMapping("/api/v1/health")
    public ApiResponse<String> health() {
        return ApiResponse.ok("Ok");
    }
}
