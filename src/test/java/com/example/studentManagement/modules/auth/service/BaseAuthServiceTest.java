package com.example.studentManagement.modules.auth.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.studentManagement.modules.auth.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public abstract class BaseAuthServiceTest {

  @Mock
  protected UserRepository userRepository;

  @InjectMocks
  protected AuthService authService;

}
