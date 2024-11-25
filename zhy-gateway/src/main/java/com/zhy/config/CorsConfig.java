package com.zhy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @Author: jobury
 * @Date: 2024/9/26 17:10
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 这里仅为了说明问题，配置为放行所有域名，生产环境请对此进行修改
        config.addAllowedOriginPattern("*");
        // 放行的请求头
        config.addAllowedHeader("*");
        // 放行的请求类型，有 GET, POST, PUT, DELETE, OPTIONS
        config.addAllowedMethod("*");
        // 暴露头部信息
        config.addExposedHeader("*");
        // 是否允许发送 Cookie
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }

}
