package com.example.studentManagement.modules.department.entity;

import java.util.List;

import com.example.studentManagement.modules.teacher.entity.TeacherEntity;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_code", nullable = false, unique = true, length = 20)
    private String departmentCode;

    @Column(name = "department_name", nullable = false, unique = true, length = 20)
    private String departmentName;

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL)
    private List<TeacherEntity> teacherEntities;
}
