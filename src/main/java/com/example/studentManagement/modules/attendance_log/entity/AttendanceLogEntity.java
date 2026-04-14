package com.example.studentManagement.modules.attendance_log.entity;

import java.time.LocalDate;

import com.example.studentManagement.modules.attendance_log.enums.StatusType;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance_logs")
public class AttendanceLogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "attend_date", nullable = false)
  private LocalDate attendDate;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusType status;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private StudentEntity studentEntity;

  @ManyToOne
  @JoinColumn(name = "subject_id", nullable = false)
  private SubjectEntity subjectEntity;
  
}