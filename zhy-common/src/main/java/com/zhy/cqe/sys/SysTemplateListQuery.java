package com.zhy.cqe.sys;

import com.zhy.cqe.common.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/20 16:46
 */

@Data
public class SysTemplateListQuery extends PageQuery {

    @Schema(description = "模板类型", example = "1")
    private String templateType;

    @Schema(description = "模板名称", example = "森海简历模板")
    private String templateName;

    @Schema(description = "船东id", example = "1")
    private Long shipownerId;

}
