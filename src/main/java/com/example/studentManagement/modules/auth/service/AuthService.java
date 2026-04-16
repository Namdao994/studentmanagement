package com.example.studentManagement.modules.auth.service;

import com.example.studentManagement.modules.auth.dto.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.AuthRegisterResponse;

public interface AuthService {
   public AuthRegisterResponse register(AuthRegisterRequest request);
}
