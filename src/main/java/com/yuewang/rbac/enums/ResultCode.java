package com.yuewang.rbac.enums;

import lombok.Getter;

/**
 * @ClassName ResultCode
 * @Description response code (for business)
 * @Author Yue Wang
 * @Date 2023/5/8 17:25
 **/
@Getter
public enum ResultCode {

    //success
    SUCCESS(0000, "SUCCESS"),
    // token
    UNAUTHORIZED(1001, "Not logged in"),
    INVALID_TOKEN(1002, "Invalid token"),
    TOKEN_EXPIRED(1003, "Expired token"),
    // permission
    FORBIDDEN(1004, "No relevant permission"),
    UNAUTHORIZED_OPERATION(1005, "Unauthorised operation"),
    // validation
    VALIDATE_FAILED(1006, "Parameter verification failed"),

    // not found
    RESOURCE_NOT_FOUND(1007, "Resource not found"),
    USER_NOT_FOUND(1008, "User not found"),
    ROLE_NOT_FOUND(1009, "Role not found"),
    PERMISSION_NOT_FOUND(1010, "Permission not found"),

    // others
    FAILED(2001, "Operation failed"),
    DATABASE_ERROR(2002, "Database operation error"),

    ERROR(5000, "Unknown error"),

    METHOD_NOT_ALLOWED(405, "Method not allowed!"),
    BAD_REQUEST(400, "Bad Request");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
