package com.zhy.cqe.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/19 18:10
 */
@Data
public class BasicParam {

    @Schema(description = "用户ID", example = "1")
    @NotNull(message = "user id can not be null")
    private Long userId;

}
