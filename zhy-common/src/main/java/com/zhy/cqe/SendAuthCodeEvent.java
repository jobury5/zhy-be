package com.zhy.cqe;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendAuthCodeEvent {

    //@NotBlank(message = "user name can not be null")
    @Schema(description = "区号", example = "86", defaultValue = "86")
    private Integer areaNumber;

    @Schema(description = "手机号码", example = "13333333333")
    @NotBlank(message = "mobile can not be null")
    private String mobile;



}
