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

| Trường      | Kiểu    | Bắt buộc   | Min   | Max  | Mô tả |
| ---         | ---     | ---        | ---   | ---  | ---   |
| username    | String  | true       | 6     | 50   | Tên đăng nhập |
| password    | String  | true       | 6     | 50   | Mật khẩu |
| fullName    | String  | true       | 6     | 50   | Họ và tên |
| role        | String  | true       | -     | -    | Vai trò (STUDENT, TEACHER) |
| gender      | Enum    | true       | -     | -    | Giới tính (MALE, FEMALE) |
| dateOfBirth | Date    | true       | -     | -    | Ngày sinh |

## Response

### Response Body

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

## Response Example

```json
{
    "success": true,
    "data": {
        "username": "student",
        "fullName": "student",
        "gender": "MALE",
        "role": "STUDENT",
        "dateOfBirth": "2000-01-01"
    },
    "meta": {
        "timestamp": "2022-01-01T00:00:00.000Z"
    }
}
```

