package com.zhy.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author jobury
 * @date 2024/9/26 14:15
 */
@Component
public class RouterValidator {
    public static final List<String> WHITE_URIS = Arrays.asList("/api/token/**", "/api/health/**", "/api/v3/**");

    public static final Predicate<ServerHttpRequest> NEED_CHECK_TOKEN =
        request -> WHITE_URIS.stream().noneMatch(uri -> request.getURI().getPath().matches(uri.replace("**", ".*")));
}
