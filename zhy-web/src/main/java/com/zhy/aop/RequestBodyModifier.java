package com.zhy.aop;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jobury
 * @Date: 2024/9/29 15:43
 */
@ControllerAdvice
public class RequestBodyModifier extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 支持所有类型的请求体
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType)  {
        HttpHeaders headers = inputMessage.getHeaders();
        if(headers.containsKey("user-id")){
            String userId = headers.get("user-id").get(0);
            if (StrUtil.isNotBlank(userId)) {
                // 检查请求体是否为空
                if (body == null) {
                    body = new HashMap<>(); // 如果请求体为空，使用一个空的 Map
                }
                // 将 user-id 添加到请求体中
                return addUserIdToRequestBody(body, userId, parameter.getParameterType());
            }
        }
        return body;
    }

    private Object addUserIdToRequestBody(Object body, String userId, Class<?> parameterType) {
//        // 将请求体转换为 Map
//        Map requestBody = objectMapper.convertValue(body, Map.class);
//        // 将 user-id 添加到请求体中
//        requestBody.put("userId", userId);
//        return objectMapper.convertValue(requestBody, parameterType);
        Map requestBody = JSON.parseObject(JSON.toJSONString(body), Map.class);
        requestBody.put("userId", userId);
        return JSON.toJavaObject(new JSONObject(requestBody), parameterType);
    }

}
