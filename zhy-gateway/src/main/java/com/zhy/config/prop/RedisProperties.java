package com.zhy.config.prop;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @Author: jobury
 * @Date: 2024/9/11 19:15
 */

@Component
@ConfigurationProperties(prefix = "spring.redis")
@Validated
@Data
public class RedisProperties {

    @NotBlank
    private String host;

    @NotBlank
    private String port;

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
