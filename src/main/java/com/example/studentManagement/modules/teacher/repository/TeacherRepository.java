package com.example.studentManagement.modules.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentManagement.modules.teacher.entity.TeacherEntity;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long>{
    
}
