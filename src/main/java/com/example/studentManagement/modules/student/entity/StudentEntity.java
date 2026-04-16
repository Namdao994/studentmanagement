package com.example.studentManagement.modules.student.entity;

import java.time.LocalDate;
import java.util.List;

import com.example.studentManagement.modules.attendance_log.entity.AttendanceLogEntity;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.classes.entity.ClassEntity;
import com.example.studentManagement.modules.enrollment.entity.EnrollmentEntity;
import com.example.studentManagement.modules.student.enums.GenderType;
import com.example.studentManagement.modules.student.enums.StatusType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "students")
public class StudentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "student_code", length = 20, nullable = false, unique = true)
  private String studentCode;

  @Column(name = "full_name", length = 100, nullable = false)
  private String fullName;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  private GenderType gender = GenderType.MALE;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusType status = StatusType.STUDYING;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id", unique = true)
  private UserEntity userEntity;

  @ManyToOne
  @JoinColumn(name = "class_id", nullable = false)
  private ClassEntity clazzEntity;

  @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
  private List<EnrollmentEntity> enrollmentEntities;

  @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
  private List<AttendanceLogEntity> attendanceLogEntities;

}
