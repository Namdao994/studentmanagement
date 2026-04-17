package com.example.studentManagement.factory;

import java.util.ArrayList;
import java.util.List;

import com.example.studentManagement.modules.attendance_log.entity.AttendanceLogEntity;
import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.clazz.entity.ClassEntity;
import com.example.studentManagement.modules.enrollment.entity.EnrollmentEntity;
import com.example.studentManagement.modules.student.entity.StudentEntity;
import com.example.studentManagement.modules.student.enums.StatusType;

public class StudentEntityTestFactory {

  private Long id = 1L;
  private String studentCode = "12345678";
  private StatusType status = StatusType.PENDING;
  private UserEntity userEntity = null;
  private ClassEntity classEntity = null;
  private List<AttendanceLogEntity> attendanceLogEntities = new ArrayList<>();
  private List<EnrollmentEntity> enrollmentEntities = new ArrayList<>();

  public static StudentEntityTestFactory builder() {
    return new StudentEntityTestFactory();
  }

  public StudentEntityTestFactory withUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
    return this;
  }

  public StudentEntityTestFactory withClazzEntity(ClassEntity classEntity) {
    this.classEntity = classEntity;
    return this;
  }

  public StudentEntityTestFactory withAttendanceLogEntities(List<AttendanceLogEntity> attendanceLogEntities) {
    this.attendanceLogEntities = attendanceLogEntities;
    return this;
  }

  public StudentEntityTestFactory withEnrollmentEntities(List<EnrollmentEntity> enrollmentEntities) {
    this.enrollmentEntities = enrollmentEntities;
    return this;
  }

  public StudentEntity build() {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setId(id);
    studentEntity.setStudentCode(studentCode);
    studentEntity.setStatus(status);
    studentEntity.setUserEntity(userEntity);
    studentEntity.setClazzEntity(classEntity);
    studentEntity.setAttendanceLogEntities(attendanceLogEntities);
    studentEntity.setEnrollmentEntities(enrollmentEntities);
    return studentEntity;
  }
  
}
