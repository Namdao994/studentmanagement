package com.example.studentManagement.factory;

import java.util.HashSet;
import java.util.Set;

import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.role.entity.RoleEntity;
import com.example.studentManagement.modules.role.enums.RoleType;

public class RoleEntityTestFactory {
    private Long id = 1L;
    private RoleType name = RoleType.ADMIN;
    private Set<UserEntity> userEntities = new HashSet<>();

    public static RoleEntityTestFactory builder() {
        return new RoleEntityTestFactory();
    }

    public RoleEntityTestFactory withName(RoleType name){
        this.name = name;
        return this;
    }
    
    public RoleEntityTestFactory withUserEntities(Set<UserEntity> userEntities){
        this.userEntities = userEntities;
        return this;
    }
    
    public RoleEntity build() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(id);
        roleEntity.setName(name);
        roleEntity.setUserEntities(userEntities);
        
        return roleEntity;
    }
}
