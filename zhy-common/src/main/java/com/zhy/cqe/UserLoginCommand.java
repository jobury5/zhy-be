package com.zhy.cqe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginCommand {

    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "user name can not be null")
    private String userName;

    @Schema(description = "密码", example = "111")
    @NotBlank(message = "password can not be null")
    private String password;

}
