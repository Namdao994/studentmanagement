package com.example.studentManagement.modules.clazz.entity;

import java.util.List;

import com.example.studentManagement.modules.student.entity.StudentEntity;
import com.example.studentManagement.modules.teacher.entity.TeacherEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classes")
public class ClassEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "class_code", nullable = false, length = 20, unique = true)
  private String classCode;

  @Column(name = "class_name", nullable = false, length = 100)
  private String className;

  @Column(name = "department", nullable = false, length = 100)
  private String department;

  @OneToMany(mappedBy = "clazzEntity", cascade = CascadeType.ALL)
  private List<StudentEntity> studentEntities;

  @OneToOne(mappedBy = "clazzEntity")
  private TeacherEntity teacherEntity;

}
