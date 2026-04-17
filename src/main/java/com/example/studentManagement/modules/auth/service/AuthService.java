package com.example.studentManagement.modules.auth.service;

import com.example.studentManagement.modules.auth.dto.request.AuthLoginRequest;
import com.example.studentManagement.modules.auth.dto.request.AuthRegisterRequest;
import com.example.studentManagement.modules.auth.dto.response.AuthLoginResponse;
import com.example.studentManagement.modules.auth.dto.response.AuthRegisterResponse;

public interface AuthService {
   public AuthRegisterResponse register(AuthRegisterRequest request);

   public AuthLoginResponse login(AuthLoginRequest request);
}
