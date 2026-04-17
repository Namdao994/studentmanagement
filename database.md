```sql
CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE classes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    class_code VARCHAR(20) NOT NULL UNIQUE,
    class_name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL
);

CREATE TABLE subjects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subject_code VARCHAR(20) NOT NULL UNIQUE,
    subject_name VARCHAR(100) NOT NULL,
    credit INT NOT NULL
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
    full_name VARCHAR(100) NOT NULL,
    gender VARCHAR(20) NOT NULL DEFAULT 'MALE',
    date_of_birth DATE NOT NULL,

    CONSTRAINT chk_gender CHECK (gender IN ("MALE", "FEMALE"))
);

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,

    CONSTRAINT chk_name CHECK (name IN ("ADMIN", "STUDENT", "TEACHER"))
)

CREATE TABLE users_roles (
    user_id BIGINT,
    role_id BIGINT,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
)

CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL DEFAULT 'STUDYING',
    class_id BIGINT,
    user_id BIGINT NOT NULL UNIQUE,

    CONSTRAINT fk_student_class FOREIGN KEY (class_id) REFERENCES classes(id) ON DELETE CASCADE,
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT chk_status CHECK (status IN ("STUDYING", "GRADUATED", "DROPPED"))
);

CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_code VARCHAR(20) NOT NULL UNIQUE,
    department_name VARCHAR(100) NOT NULL
);

CREATE TABLE teachers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    teacher_code VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(50),

    department_id BIGINT,
    user_id BIGINT NOT NULL UNIQUE,
    class_id BIGINT UNIQUE,

    CONSTRAINT fk_teacher_department
        FOREIGN KEY (department_id) REFERENCES departments(id),

    CONSTRAINT fk_teacher_user
        FOREIGN KEY (user_id) REFERENCES users(id)

    CONSTRAINT fk_teacher_class FOREIGN KEY (class_id) REFERRENCE classes(id)

    CONSTRAINT chk_title CHECK (title IN ('LECTURER', 'SENIOR_LECTURER', 'ASSISTANT_LECTURER', 'HEAD_OF_DEPARTMENT'))
);

CREATE TABLE enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    semester VARCHAR(20) NOT NULL DEFAULT "SEMESTER_1",
    year INT NOT NULL CHECK (year >= 2000 AND year <= 2100),
    score DECIMAL(4, 2) NOT NULL,
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    CONSTRAINT fk_enrollment_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
    CONSTRAINT chk_semester CHECK (semester IN ("SEMESTER_1", "SEMESTER_2"))
);

CREATE TABLE attendance_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attend_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    CONSTRAINT fk_attendance_student FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    CONSTRAINT fk_attendance_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);
```
