package com.yuewang.rbac.enums;

import lombok.Getter;

/**
 * @ClassName ResultCode
 * @Description response code
 * @Author Yue Wang
 * @Date 2023/5/8 17:25
 **/
@Getter
public enum ResultCode {

    //success
    SUCCESS(0000, "操作成功"),
    // token
    UNAUTHORIZED(1001, "没有登录"),
    INVALID_TOKEN(1002, "无效的token"),
    TOKEN_EXPIRED(1003, "token已过期"),
    // permission
    FORBIDDEN(1004, "没有相关权限"),
    UNAUTHORIZED_OPERATION(1005, "未授权的操作"),
    // validation
    VALIDATE_FAILED(1006, "参数校验失败");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
