package com.example.studentManagement.modules.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentManagement.modules.role.entity.RoleEntity;
import com.example.studentManagement.modules.role.enums.RoleType;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
    Optional<RoleEntity> findByName(RoleType name);
}
