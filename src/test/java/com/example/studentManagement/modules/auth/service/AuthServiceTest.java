package com.example.studentManagement.modules.auth.service;

import org.junit.jupiter.api.Test;

import com.example.studentManagement.factory.UserEntityTestFactory;
import com.example.studentManagement.modules.auth.entity.UserEntity;

public class AuthServiceTest extends BaseAuthServiceTest {
  
  @Test
  public void register_shouldSaveUser_whenValidRequest() {
    // given
    UserEntity userEntity = UserEntityTestFactory.builder().build();
    
    // when
  }
}
