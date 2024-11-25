package com.zhy.cqe.token;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/25 14:05
 */
@Data
public class UserByAccessTokenQuery {

    @Schema(description = "登录令牌", example = "415752d236174f36b0b3f8ef452c5be7")
    @NotBlank(message = "token can not be null")
    private String token;


}
