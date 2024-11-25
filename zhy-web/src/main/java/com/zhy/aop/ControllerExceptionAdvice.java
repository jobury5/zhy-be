package com.zhy.aop;

import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import com.zhy.result.ZhyResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    //    参数校验异常
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ZhyResult validationExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        String msg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(n -> String.format("%s: %s", n.getField(), 		      n.getDefaultMessage()))
                .reduce((x, y) -> String.format("%s; %s", x, y))
                .orElse("参数输入有误");
        //log.error("BindException异常，参数校验异常：{}", msg);
        return ZhyResult.of(SysCode.PARAM_VALIDATE_ERROR, msg);
    }

    @ExceptionHandler(ApiException.class)
    public ZhyResult apiExceptionHandler(ApiException e){
        e.printStackTrace();
        return ZhyResult.of(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ZhyResult exceptionHandler(Exception e){
        e.printStackTrace();
        return ZhyResult.of(SysCode.FAILED, e.getMessage());
    }

}