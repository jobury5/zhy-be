package com.zhy.cqe.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/19 15:38
 */

@Data
public class PageQuery extends BasicParam {

    @Schema(description = "页码", example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1,message = "页码不能小于1")
    private int pageNo = 1;


    @Schema(description = "每页条数", example = "10")
    @NotNull(message = "条数不能为空")
    @Max(value = 100,message = "条数不能大于100条")
    @Min(value = 1,message = "条数不能小于1条")
    private int pageSize = 10;


}
