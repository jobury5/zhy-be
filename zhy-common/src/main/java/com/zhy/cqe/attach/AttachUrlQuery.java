package com.zhy.cqe.attach;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/23 15:16
 */

@Data
public class AttachUrlQuery extends BasicParam {

    @Schema(description = "附件id", example = "1")
    @NotNull(message = "attach id can not be null")
    private Long attachId;

}
