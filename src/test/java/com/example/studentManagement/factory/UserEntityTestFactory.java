package com.example.studentManagement.factory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.role.entity.RoleEntity;
import com.example.studentManagement.modules.student.entity.StudentEntity;
import com.example.studentManagement.modules.teacher.entity.TeacherEntity;

public class UserEntityTestFactory {

  private Long id = 1L;
  private String username = "username";
  private String password = "12345678";
  private String fullName = "User Name";
  private GenderType gender = GenderType.MALE;
  private LocalDate dateOfBirth = LocalDate.now().minusYears(23);
  private Boolean isActive = false;
  private StudentEntity studentEntity = null;
  private TeacherEntity teacherEntity = null;
  private Set<RoleEntity> roleEntities = new HashSet<RoleEntity>();
  
  public static UserEntityTestFactory builder() {
    return new UserEntityTestFactory();
  }

  public UserEntityTestFactory withStudentEntity(StudentEntity studentEntity) {
    this.studentEntity = studentEntity;
    return this;
  }

  public UserEntityTestFactory withTeacherEntity(TeacherEntity teacherEntity) {
    this.teacherEntity = teacherEntity;
    return this;
  }

  public UserEntityTestFactory withRoleEntities(Set<RoleEntity> roleEntities) {
    this.roleEntities = roleEntities;
    return this;
  }

  public UserEntity build() {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(id);
    userEntity.setUsername(username);
    userEntity.setPassword(password);
    userEntity.setFullName(fullName);
    userEntity.setGender(gender);
    userEntity.setDateOfBirth(dateOfBirth);
    userEntity.setIsActive(isActive);
    userEntity.setStudentEntity(studentEntity);
    userEntity.setTeacherEntity(teacherEntity);
    userEntity.setRoleEntities(roleEntities);
    return userEntity;
  }


}
