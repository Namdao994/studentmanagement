package com.example.studentManagement.modules.enrollment.entity;

import java.math.BigDecimal;

import com.example.studentManagement.modules.enrollment.enums.SemesterType;
import com.example.studentManagement.modules.student.entity.StudentEntity;
import com.example.studentManagement.modules.subject.entity.SubjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrollments")
public class EnrollmentEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "semester", nullable = false)
  @Enumerated(EnumType.STRING)
  private SemesterType semester = SemesterType.SEMESTER_1;

  @Min(2000)
  @Max(2100)
  @Column(name = "year", nullable = false)
  private int year;

  @Column(name = "score", precision = 4, scale = 2, nullable = false)
  private BigDecimal score;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private StudentEntity studentEntity;

  @ManyToOne
  @JoinColumn(name = "subject_id", nullable = false)
  private SubjectEntity subjectEntity;
}
