package com.zhy.aop;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import com.zhy.result.ZhyResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: jobury
 * @Date: 2024/9/10 15:30
 */

@RestControllerAdvice(basePackages = "com.zhy.web")
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !methodParameter.getParameterType().isAssignableFrom(ZhyResult.class) && !methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        if(returnType.getGenericParameterType().equals(String.class)){
            try {
                return JSON.toJSONString(ZhyResult.success(data));
            }
            catch (JSONException e){
                throw new ApiException(SysCode.RESPONSE_PACK_ERROR, e.getMessage());
            }
        }
        return ZhyResult.success(data);
    }
}
