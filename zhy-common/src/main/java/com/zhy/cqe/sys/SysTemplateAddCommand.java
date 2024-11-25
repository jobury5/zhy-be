package com.zhy.cqe.sys;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/20 16:47
 */

@Data
public class SysTemplateAddCommand extends BasicParam {

    @Schema(description = "模板类型", example = "1")
    @NotEmpty(message = "template type can not be null")
    private String templateType;

    @Schema(description = "模板名称", example = "森海简历模板")
    @NotEmpty(message = "template name can not be null")
    private String templateName;

    @Schema(description = "船东id", example = "1")
    private Long shipownerId;

    @Schema(description = "文档类型", example = "word")
    @NotEmpty(message = "document type can not be null")
    private String docType;

    @Schema(description = "备注", example = "")
    private String remark;

    @Schema(description = "附件id", example = "1")
    @NotNull(message = "attachment id can not be null")
    private Long attachId;
}
