package com.zhy.util;

import com.alibaba.fastjson2.JSON;
import com.zhy.result.StatusCode;
import com.zhy.result.ZhyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author: jobury
 * @Date: 2024/9/26 10:28
 */
@Slf4j
public class WebFluxUtil {

    public static Mono<Void> writeErrorResponse(ServerHttpResponse response, StatusCode statusCode) {
        //HttpStatus status = determineHttpStatus(statusCode);
        HttpStatus status = HttpStatus.OK;
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.getHeaders().setCacheControl("no-cache");

        String responseBody = JSON.toJSONString(ZhyResult.of(statusCode));
        DataBuffer buffer = response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> {
                    DataBufferUtils.release(buffer);
                    log.error("Error writing response: {}", error.getMessage());
                });
    }

//    private static HttpStatus determineHttpStatus(StatusCode statusCode) {
//        return switch (statusCode) {
//            case ACCESS_UNAUTHORIZED, TOKEN_INVALID -> HttpStatus.UNAUTHORIZED;
//            case TOKEN_ACCESS_FORBIDDEN -> HttpStatus.FORBIDDEN;
//            default -> HttpStatus.BAD_REQUEST;
//        };
//    }

}
