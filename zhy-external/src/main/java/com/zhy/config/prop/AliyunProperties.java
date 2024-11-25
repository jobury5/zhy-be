package com.zhy.config.prop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @Author: jobury
 * @Date: 2024/9/10 17:12
 */

@Component
@ConfigurationProperties(prefix = "aliyun")
@Validated
@Data
public class AliyunProperties {

    @NotBlank
    private String accessKey;

    @NotBlank
    private String accessSecret;

    @NotBlank
    @Value("${aliyun.oss.bucket}")
    private String ossBucket;

    @NotNull
    @Value("${aliyun.oss.expiration}")
    private Long ossExpiration;

    @NotBlank
    @Value("${aliyun.oss.region}")
    private String ossRegion;

    @NotBlank
    @URL
    @Value("${aliyun.oss.endpoint.internal}")
    private String ossEndpointInternal;

    @NotBlank
    @URL
    @Value("${aliyun.oss.endpoint.external}")
    private String ossEndpointExternal;

    @NotBlank
    @Value("${aliyun.ocr.endpoint")
    private String ocrEndpoint;


}
