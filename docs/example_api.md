# API: Tên API

- Mô tả:

## Đặc tả

- URL:
- Method:
- Content-Type: 

## Header

- Authorization

## Request

### Request Params
<!-- Ví dụ -->
<!-- | Trường      | Kiểu    | Bắt buộc   | Mô tả |
| ---         | ---     | ---        | ---   | -->

### Request Query
<!-- Ví dụ -->
<!-- | Trường      | Kiểu    | Bắt buộc   | Mô tả |
| ---         | ---     | ---        | ---   | -->


### Request Body
<!-- Ví dụ -->
<!-- | Trường      | Kiểu    | Bắt buộc   | Min   | Max  | Mô tả |
| ---         | ---     | ---        | ---   | ---  | ---   | -->


## Response

### Response Body
<!-- Ví dụ -->
<!-- | Trường          | Kiểu    | Bắt buộc   | Mô tả |
| ---             | ---     | ---        | ---   | 
| success         | boolean | true       | Trạng thái thành công |
| data.username   | String  | true       | Tên đăng nhập |
| data.fullName   | String  | true       | Họ và tên | -->

## Status Codes
<!-- Ví dụ -->
<!-- | HTTP Status | Code        | Message                     | Mô tả |
|-------------|-------------|-----------------------------|------|
| 201         | CREATED     | User created successfully   | Đăng ký thành công |
| 500         | ERROR       | Internal server error       | Lỗi hệ thống | -->

## Bảng cơ sở dữ liệu

- Các bảng dữ liệu liên quan

## Dữ liệu trích xuất

<!-- Chỉ dùng phần này khi Method là GET còn không thì ghi là không có-->
<!-- Ví dụ -->
<!-- - Lấy từ bảng User:
    - id -> id của người dùng
    - name -> tên của người dùng -->

## Luồng sự kiện 
<!-- Ví dụ: 1. Validate token → 2. Kiểm tra dữ liệu → 3. Lưu DB → 4. Trả response -->

## Request Example
<!-- Chỉ dùng phần này khi Method là POST, PUT, PATCH, còn không thì ghi là không có -->
<!-- Ví dụ -->
<!-- ```json
{
    "username": "namdao",
    "password": "12345678",
    "fullName": "Dao Nhat Nam",
    "gender": "MALE",
    "role": "STUDENT",
    "dateOfBirth": "2000-01-01"
}
``` -->

## Response Example

### Susccess Response
<!-- Ví dụ -->
<!-- ```json
{
    "success": true,
    "data": {
       
    },
    "error": null,
    "meta": {
        "timestamp": "2026-04-17T01:07:56.842888500Z"
    }
}
```

### Error Response
<!-- Ví dụ -->
<!-- ```json
{
    "success": false,
    "data": null,
    "error": {
        "code": "ERROR",
        "message": "Internal server error"
    },
    "meta": {
        "timestamp": "2026-04-17T01:07:56.842888500Z"
    }
}
``` -->