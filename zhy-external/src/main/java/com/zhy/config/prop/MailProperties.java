package com.zhy.config.prop;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @Author: jobury
 * @Date: 2024/9/11 19:15
 */

@Component
@ConfigurationProperties(prefix = "mail")
@Validated
@Data
public class MailProperties {

    @NotBlank
    private String user;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Value("${mail.smtp.host}")
    private String smtpHost;

    @NotBlank
    @Value("${mail.smtp.from}")
    private String smtpFrom;


}
