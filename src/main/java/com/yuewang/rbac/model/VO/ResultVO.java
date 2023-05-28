package com.yuewang.rbac.model.VO;

import com.yuewang.rbac.enums.ExceptionCode;
import com.yuewang.rbac.enums.ResultCode;
import lombok.Getter;

/**
 * @ClassName ResultVO
 * @Description Customize unified response body.
 * @Author Yue Wang
 * @Date 2023/5/27 21:58
 **/
@Getter
public class ResultVO<T> {

    /**
     * Status code
     */
    private int code;
    /**
     * Response message to indicate response status.
     */
    private String msg;
    /**
     * Detailed information in response.
     */
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public ResultVO(ExceptionCode annotation, T data) {
        this.code = annotation.value();
        this.msg = annotation.message();
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("{\"code\":%d,\"msg\":\"%s\",\"data\":\"%s\"}", code, msg, data.toString());
    }
}