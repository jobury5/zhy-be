package com.zhy.cqe.sys;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/20 17:25
 */

@Data
public class SysTemplateDetailQuery extends BasicParam {

    @Schema(description = "模板id", example = "1")
    @NotNull(message = "template id can not be null")
    private Long templateId;

}
