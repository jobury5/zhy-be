package com.zhy.cqe.sys;

import com.zhy.cqe.common.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/19 11:29
 */

@Data
public class SysCertListQuery extends PageQuery {

    @Schema(description = "证书大类", example = "1")
    private String certType;

    @Schema(description = "证书名称")
    private String certName;

}
