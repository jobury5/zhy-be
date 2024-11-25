package com.zhy.domain.entity.sys;

import com.zhy.types.Code;
import com.zhy.types.Id;
import com.zhy.types.Name;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/18 18:00
 */

@Data
public class SysCert {

    private DictData certType;

    private Id certId;

    private Name certName;

    private Name certEnName;

    private SysCert parentSysCert;

    private Code simpleCode;

    private Long aliTemplateId;

    private Integer validYears;

    private Integer warnDays;

}
