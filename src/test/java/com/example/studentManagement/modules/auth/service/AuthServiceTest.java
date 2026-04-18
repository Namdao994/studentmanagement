package com.example.studentManagement.modules.auth.service;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.studentManagement.factory.RoleEntityTestFactory;
import com.example.studentManagement.factory.UserEntityTestFactory;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.role.entity.RoleEntity;

public class AuthServiceTest extends BaseAuthServiceTest {

  @Test
  public void register_shouldSaveUser_whenValidRequest() {
    // given
    UserEntity userEntity = UserEntityTestFactory.builder().build();
    RoleEntity roleEntity = RoleEntityTestFactory.builder().build();
    Set<RoleEntity> roleEntities = Set.of(roleEntity);

    // when
  }
}
