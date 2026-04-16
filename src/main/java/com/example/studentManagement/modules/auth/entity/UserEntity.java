package com.example.studentManagement.modules.auth.entity;

import java.time.LocalDate;
import java.util.Set;

import com.example.studentManagement.modules.auth.enums.GenderType;
import com.example.studentManagement.modules.role.entity.RoleEntity;
import com.example.studentManagement.modules.student.entity.StudentEntity;
import com.example.studentManagement.modules.teacher.entity.TeacherEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", length = 50, nullable = false, unique = true)
  private String username;

  @Column(name = "password", length = 255, nullable = false)
  private String password;

  @Column(name = "full_name", length = 100, nullable = false)
  private String fullName;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private GenderType gender = GenderType.MALE;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = false;

  @OneToOne(mappedBy = "userEntity")
  private StudentEntity studentEntity;

  @OneToOne(mappedBy = "userEntity")
  private TeacherEntity teacherEntity;

  @ManyToMany
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roleEntities;
}
