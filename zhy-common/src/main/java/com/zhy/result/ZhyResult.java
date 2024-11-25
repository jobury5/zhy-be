package com.zhy.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class ZhyResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;



    private int code;
    private String msg;
    private T data;

//    private static final int HTTP_CODE_SUCCESS = 200;
//    private static final int HTTP_CODE_FAIL = 400;
//
//    private static final String HTTP_MESSAGE_SUCCESS = "success";
//    private static final String HTTP_MESSAGE_FAIL = "fail";

    public ZhyResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> ZhyResult<T> success(T data) {
        return new ZhyResult(SysCode.SUCCESS.getCode(), SysCode.SUCCESS.getMsg(), data);
    }

    public static <T> ZhyResult<T> fail() {
        return new ZhyResult(SysCode.FAILED.getCode(), SysCode.FAILED.getMsg(), null);
    }

    public static <T> ZhyResult<T> fail(String msg) {
        return new ZhyResult(SysCode.FAILED.getCode(), msg, null);
    }

//    public static <T> ZhyResult<T> fail(int code, String msg) {
//        return new ZhyResult(code, msg, null);
//    }

    public static <T> ZhyResult<T> of(int code, String msg, T data) {
        return new ZhyResult(code, msg, data);
    }
    public static <T> ZhyResult<T> of(StatusCode statusCode, T data) {
        return new ZhyResult(statusCode.getCode(), statusCode.getMsg(), data);
    }

    public static <T> ZhyResult<T> of(StatusCode statusCode) {
        return new ZhyResult(statusCode.getCode(), statusCode.getMsg(), null);
    }

}
