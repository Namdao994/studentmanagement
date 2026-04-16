package com.example.studentManagement.modules.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentManagement.modules.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
