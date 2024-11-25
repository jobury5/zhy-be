package com.zhy.cqe.sys;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/19 17:24
 */

@Data
public class SysCertDetailQuery extends BasicParam {

    @Schema(description = "证书类型id", example = "1")
    @NotNull(message = "cert id can not be null")
    private Long certId;

}
