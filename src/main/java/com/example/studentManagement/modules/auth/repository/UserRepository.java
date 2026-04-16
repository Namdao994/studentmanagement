package com.example.studentManagement.modules.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentManagement.modules.auth.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
