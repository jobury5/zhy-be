package com.zhy.cqe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginByAuthCodeCommand {

    @Schema(description = "区号", example = "86", defaultValue = "86")
    private Integer areaNumber;

    @Schema(description = "区号", example = "86", defaultValue = "86")
    @NotBlank(message = "mobile can not be null")
    private String mobile;

    @Schema(description = "验证码", example = "1234")
    @NotBlank(message = "auth code can not be null")
    private String authCode;

}
