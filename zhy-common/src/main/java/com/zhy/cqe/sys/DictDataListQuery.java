package com.zhy.cqe.sys;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/18 17:24
 */
@Data
public class DictDataListQuery extends BasicParam {

    @Schema(description = "字典类型", example = "cert_type")
    @NotBlank(message = "dict type can not be null")
    private String dictType;

}
