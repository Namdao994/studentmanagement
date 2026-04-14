package com.example.studentManagement.modules.subject.entity;

import java.util.List;

import com.example.studentManagement.modules.attendance_log.entity.AttendanceLogEntity;
import com.example.studentManagement.modules.enrollment.entity.EnrollmentEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "subjects")
public class SubjectEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "subject_code", length = 20, nullable = false, unique = true)
  private String subjectCode;

  @Column(name = "subject_name", length = 100, nullable = false)
  private String subjectName;

  @Column(name = "credit", nullable = false)
  private int credit;

  @OneToMany(mappedBy = "subjectEntity", cascade = CascadeType.ALL)
  private List<EnrollmentEntity> enrollmentEntities;

  @OneToMany(mappedBy = "subjectEntity", cascade = CascadeType.ALL)
  private List<AttendanceLogEntity> attendanceLogEntities;
}
