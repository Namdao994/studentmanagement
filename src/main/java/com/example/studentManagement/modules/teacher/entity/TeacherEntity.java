package com.example.studentManagement.modules.teacher.entity;

import com.example.studentManagement.modules.auth.entity.UserEntity;
import com.example.studentManagement.modules.clazz.entity.ClassEntity;
import com.example.studentManagement.modules.department.entity.DepartmentEntity;
import com.example.studentManagement.modules.teacher.enums.TitleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_code", nullable = false, unique = true, length = 20)
    private String teacherCode;

    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private TitleType title = TitleType.ASSISTANT_LECTURER;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;

    @OneToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", unique = true)
    private ClassEntity clazzEntity;
}
