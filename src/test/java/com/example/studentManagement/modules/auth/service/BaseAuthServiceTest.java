package com.example.studentManagement.modules.auth.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.studentManagement.modules.auth.repository.UserRepository;
import com.example.studentManagement.modules.auth.service.impl.AuthServiceImpl;
import com.example.studentManagement.modules.role.repository.RoleRepository;
import com.example.studentManagement.modules.student.repository.StudentRepository;
import com.example.studentManagement.modules.teacher.repository.TeacherRepository;
import com.example.studentManagement.modules.auth.mapper.UserMapper;
import com.example.studentManagement.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public abstract class BaseAuthServiceTest {

  @Mock
  protected UserRepository userRepository;
  @Mock
  protected PasswordEncoder passwordEncoder;
  @Mock
  protected RoleRepository roleRepository;
  @Mock
  protected StudentRepository studentRepository;
  @Mock
  protected TeacherRepository teacherRepository;
  @Mock
  protected UserMapper userMapper;
  @Mock
  protected JwtUtil jwtUtil;

  @InjectMocks
  protected AuthServiceImpl authService;

}
