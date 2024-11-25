package com.zhy.cqe.sys;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/20 11:30
 */
@Data
public class SysCertUpdateCommand extends BasicParam {

    @Schema(description = "证书类型id", example = "1")
    @NotNull(message = "cert id can not be null")
    private Long certId;

    @Schema(description = "证书大类", example = "1")
    @NotEmpty(message = "cert type can not be null")
    private String certType;

    @Schema(description = "上级证书类型id", example = "1")
    private Long parentCertId;

    @Schema(description = "证书名称", example = "甲板部COC证书")
    @NotEmpty(message = "cert name can not be null")
    private String certName;

    @Schema(description = "证书英文名称")
    //@Pattern(regexp = ENGLISH_REGEX, message = "English name must contain only English letters")
    @NotEmpty(message = "cert english name can not be null")
    private String certEnName;

    @Schema(description = "证书简称", example = "COC")
    @NotEmpty(message = "simple code can not be null")
    private String simpleCode;

    @Schema(description = "有效时间（年）", example = "1")
    private Integer validYears;

    @Schema(description = "提前警告天数", example = "30")
    private Integer warnDays;
}
