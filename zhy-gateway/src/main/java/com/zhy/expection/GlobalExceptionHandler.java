package com.zhy.expection;

import com.alibaba.fastjson2.JSON;
import com.zhy.exception.ApiException;
import com.zhy.result.ZhyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author: jobury
 * @Date: 2024/9/29 17:50
 */
@Configuration
@Slf4j
public class GlobalExceptionHandler {

    @Bean
    @Order(-1) // 确保该处理器优先级最高
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        return new ErrorWebExceptionHandler() {
            @Override
            public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
                // 记录异常日志
                log.error(ex.getMessage());
                // 设置响应体,响应状态码
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                exchange.getResponse().setStatusCode(HttpStatus.OK);
                if(ex instanceof ApiException){
                    ApiException apiException = (ApiException) ex;
                    ZhyResult zhyResult = new ZhyResult();
                    zhyResult.setCode(apiException.getCode());
                    zhyResult.setMsg(apiException.getMsg());
                    zhyResult.setData(apiException.getMessage());
                    String jsonString = JSON.toJSONString(zhyResult);
                    byte[] bytes = jsonString.getBytes(StandardCharsets.UTF_8);
                    return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
                }
                //抛出兜底异常
                ZhyResult zhyResult = ZhyResult.fail();
                String jsonString = JSON.toJSONString(zhyResult);
                byte[] bytes = jsonString.getBytes(StandardCharsets.UTF_8);
                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
            }
        };
    }

}
