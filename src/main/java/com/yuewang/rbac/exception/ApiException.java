package com.yuewang.rbac.exception;

import com.yuewang.rbac.enums.ResultCode;
import lombok.Getter;

/**
 * @ClassName ApiException
 * @Description Customized exception class
 * @Author Yue Wang
 * @Date 2023/5/8 17:22
 **/
@Getter
public class ApiException extends Throwable {

    private ResultCode resultCode;
    private String msg;

    public ApiException(ResultCode resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
    }

    public ApiException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.msg = resultCode.getMsg();
    }

}
