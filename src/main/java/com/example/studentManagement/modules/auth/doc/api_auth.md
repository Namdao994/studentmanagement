# API: Register

- Mô tả: Đăng kí mới người dùng với role là STUDENT hoặc là TEACHER

## Đặc tả

- URL: /api/v1/auth/register
- Method: POST
- Content-Type: application/json

## Header

- Không có

## Request

### Request Params

- không có

### Request Query

- không có

### Request Body

> Request gửi lên khác nhau tùy theo `role` của người dùng chọn.

**Trường chung (dành cho cả STUDENT và TEACHER):**

| Trường      | Kiểu    | Bắt buộc   | Min   | Max  | Mô tả |
| ---         | ---     | ---        | ---   | ---  | ---   |
| username    | String  | true       | 6     | 50   | Tên đăng nhập |
| password    | String  | true       | 6     | 50   | Mật khẩu |
| fullName    | String  | true       | 6     | 50   | Họ và tên |
| role        | String  | true       | -     | -    | Vai trò (STUDENT, TEACHER) |
| gender      | Enum    | true       | -     | -    | Giới tính (MALE, FEMALE) |
| dateOfBirth | Date    | true       | -     | -    | Ngày sinh |

**Trường riêng khi role = STUDENT:**

| Trường      | Kiểu    | Bắt buộc   | Min   | Max  | Mô tả |
| ---         | ---     | ---        | ---   | ---  | ---   |

**Trường riêng khi role = TEACHER:**

| Trường      | Kiểu    | Bắt buộc   | Min   | Max  | Mô tả |
| ---         | ---     | ---        | ---   | ---  | ---   |
| title       | Enum    | true       | -     | -    | title (LECTURER, SENIOR_LECTURER, ASSISTANT_LECTURER, HEAD_OF_DEPARTMENT) |

## Response

### Response Body

> Response trả về khác nhau tùy theo `role` của người dùng chọn.

**Trường chung (dành cho cả STUDENT và TEACHER):**

| Trường          | Kiểu    | Bắt buộc   | Mô tả |
| ---             | ---     | ---        | ---   | 
| success         | boolean | true       | Trạng thái thành công |
| data.username   | String  | true       | Tên đăng nhập |
| data.fullName   | String  | true       | Họ và tên |
| data.role       | String  | true       | Vai trò (STUDENT, TEACHER) |
| data.gender     | Enum    | true       | Giới tính (MALE, FEMALE) |
| data.isActive   | boolean | false      | Trạng thái của tài khoản |
| data.dateOfBirth| Date    | true       | Ngày sinh |
| meta.timestamp  | String  | true       | Thời gian tạo response |

**Trường riêng khi role = STUDENT:**

| Trường           | Kiểu   | Bắt buộc | Mô tả |
| ---              | ---    | ---      | ---   |
| data.studentCode | String | true     | Mã sinh viên |
| data.status      | Enum   | true     | Trạng thái học (PENDING, STUDYING, GRADUATED, DROPPED_OUT) |

**Trường riêng khi role = TEACHER:**

| Trường           | Kiểu   | Bắt buộc | Mô tả |
| ---              | ---    | ---      | ---   |
| data.teacherCode | String | true     | Mã giáo viên |
| data.title       | Enum   | true     | Học hàm / học vị (PROFESSOR, ASSOCIATE_PROFESSOR, ...) |

## Status Codes

| HTTP Status | Code        | Message                     | Mô tả |
|-------------|-------------|-----------------------------|------|
| 201         | CREATED     | User created successfully   | Đăng ký thành công |
| 400         | BAD_REQUEST | Invalid input data          | Dữ liệu không hợp lệ |
| 409         | CONFLICT    | Username already exists     | Trùng username |
| 500         | ERROR       | Internal server error       | Lỗi hệ thống |

## Bảng cơ sở dữ liệu

- User: Ghi thông tin người dùng mới
- Student: Ghi thông tin sinh viên mới

## Dữ liệu trích xuất

- Không có

## Luồng sự kiện chính

- Validate dữ liệu đầu vào
- Trả về thông tin vừa mới tạo

## Request Example

### Request (STUDENT)

```json
{
    "username": "student",
    "password": "[PASSWORD]",
    "fullName": "student",
    "gender": "MALE",
    "role": "STUDENT",
    "dateOfBirth": "2000-01-01"
}
```

### Request (TEACHER)

```json
{
    "username": "student",
    "password": "[PASSWORD]",
    "fullName": "student",
    "gender": "MALE",
    "role": "TEACHER",
    "title": "ASSISTANT_LECTURER" ,
    "dateOfBirth": "2000-01-01"
}
```

## Response Example

### Susccess Response

> Response trả về khác nhau tùy theo `role` của người dùng chọn.

### Susccess Response (STUDENT)

```json
{
    "success": true,
    "data": {
        "username": "namdao8",
        "fullName": "Dao Nhat Nam6",
        "roles": [
            "STUDENT"
        ],
        "gender": "FEMALE",
        "isActive": false,
        "dateOfBirth": "2000-01-01",
        "studentCode": "STU57c0e977",
        "status": "PENDING"
    },
    "error": null,
    "meta": {
        "timestamp": "2026-04-17T08:44:03.874601400Z"
    }
}
```

### Susccess Response (TEACHER)
```json
{
    "success": true,
    "data": {
        "username": "namdao9",
        "fullName": "Dao Nhat Nam6",
        "roles": [
            "TEACHER"
        ],
        "gender": "FEMALE",
        "isActive": false,
        "dateOfBirth": "2000-01-01",
        "title": "ASSISTANT_LECTURER",
        "teacherCode": "TCH5b4eaa6a"
    },
    "error": null,
    "meta": {
        "timestamp": "2026-04-17T09:00:29.105969800Z"
    }
}
```

### Error Response

```json
{
    "success": false,
    "data": null,
    "error": {
        "code": "ERROR",
        "message": "Internal server error"
    },
    "meta": {
        "timestamp": "2022-01-01T00:00:00.000Z"
    }
}
```

# API: Login

- Mô tả: Đăng nhập vào hệ thống

## Đặc tả

- URL: /api/v1/auth/login
- Method: POST
- Content-Type: application/json

## Header

- Không có

## Request

### Request Params

- không có

### Request Query

- không có

### Request Body

| Trường      | Kiểu    | Bắt buộc   | Min   | Max  | Mô tả |
| ---         | ---     | ---        | ---   | ---  | ---   |
| username    | String  | true       | 6     | 50   | Tên đăng nhập |
| password    | String  | true       | 6     | 50   | Mật khẩu |

## Response

### Response Body

> Response trả về khác nhau tùy theo `role` của tài khoản đăng nhập.

**Trường chung (dành cho cả STUDENT và TEACHER):**

| Trường              | Kiểu    | Bắt buộc | Mô tả |
| ---                 | ---     | ---      | ---   |
| success             | boolean | true     | Trạng thái thành công |
| data.accessToken    | String  | true     | JWT access token |
| data.refreshToken   | String  | true     | JWT refresh token |
| data.username       | String  | true     | Tên đăng nhập |
| data.fullName       | String  | true     | Họ và tên |
| data.role           | String  | true     | Vai trò (STUDENT, TEACHER) |
| data.gender         | Enum    | true     | Giới tính (MALE, FEMALE) |
| data.isActive       | Boolean | true     | Trạng thái của tài khoản |
| data.dateOfBirth    | Date    | true     | Ngày sinh |
| meta.timestamp      | String  | true     | Thời gian tạo response |

**Trường riêng khi role = STUDENT:**

| Trường           | Kiểu   | Bắt buộc | Mô tả |
| ---              | ---    | ---      | ---   |
| data.studentCode | String | true     | Mã sinh viên |
| data.status      | Enum   | true     | Trạng thái học (STUDYING, GRADUATED, DROPPED_OUT) |

**Trường riêng khi role = TEACHER:**

| Trường           | Kiểu   | Bắt buộc | Mô tả |
| ---              | ---    | ---      | ---   |
| data.teacherCode | String | true     | Mã giáo viên |
| data.title       | Enum   | true     | Học hàm / học vị (PROFESSOR, ASSOCIATE_PROFESSOR, ...) |

## Status Codes

| HTTP Status | Code        | Message                     | Mô tả |
|-------------|-------------|-----------------------------|------|
| 400         | BAD_REQUEST | Invalid input data          | Dữ liệu không hợp lệ |
| 409         | CONFLICT    | Username already exists     | Trùng username |
| 500         | ERROR       | Internal server error       | Lỗi hệ thống |

## Bảng cơ sở dữ liệu
<!-- Nếu đăng nhập bằng tài khoảng student thì là bảng Student, nếu là teacher thì là bảng Teacher -->
- User: Bảng thông tin người dùng
- Student: Bảng thông tin sinh viên
- Teacher: Bảng thông tin giáo viên

## Dữ liệu trích xuất

- Không có

## Luồng sự kiện chính

- Validate dữ liệu đầu vào
- Trả về thông tin tài khoản kèm theo accessToken và refreshToken

## Request Example

```json
{
    "username": "student",
    "password": "[PASSWORD]"
}
```

## Response Example

### Susccess Response (STUDENT)

```json
{
    "success": true,
    "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "username": "student01",
        "fullName": "Nguyễn Văn A",
        "roles": ["STUDENT"],
        "gender": "MALE",
        "isActive": true,
        "dateOfBirth": "2000-01-01",
        "studentCode": "SV001",
        "status": "STUDYING"
    },
    "meta": {
        "timestamp": "2022-01-01T00:00:00.000Z"
    }
}
```

### Susccess Response (TEACHER)

```json
{
    "success": true,
    "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "username": "teacher01",
        "fullName": "Trần Thị B",
        "roles": ["TEACHER"],
        "gender": "FEMALE",
        "isActive": true,
        "dateOfBirth": "1985-06-15",
        "teacherCode": "GV001",
        "title": "PROFESSOR"
    },
    "meta": {
        "timestamp": "2022-01-01T00:00:00.000Z"
    }
}
```

### Error Response

```json
{
    "success": false,
    "data": null,
    "error": {
        "code": "ERROR",
        "message": "Internal server error"
    },
    "meta": {
        "timestamp": "2022-01-01T00:00:00.000Z"
    }
}
```