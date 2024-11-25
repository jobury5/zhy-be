package com.zhy.exception;

import com.zhy.result.AppCode;
import com.zhy.result.StatusCode;

/**
 * @Author: jobury
 * @Date: 2024/9/10 15:35
 */

public class ApiException extends RuntimeException {

    private int code;
    private String msg;

    public ApiException(StatusCode statusCode, String message){
        super(message);
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public ApiException(StatusCode statusCode){
        super(statusCode.getMsg());
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public ApiException(String message){
        super(message);
        this.code = AppCode.APP_ERROR.getCode();
        this.msg = AppCode.APP_ERROR.getMsg();
    }

    public ApiException(int code, String message){
        super(message);
        this.code = code;
        this.msg = message;
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }



}
