package com.zhy.cqe.sys;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/29 17:25
 */
@Data
public class SysCertDeleteCommand extends BasicParam {

    @Schema(description = "证书类型id", example = "1")
    @NotNull(message = "cert id can not be null")
    private Long certId;

}
