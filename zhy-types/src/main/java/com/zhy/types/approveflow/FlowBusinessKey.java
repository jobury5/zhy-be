package com.zhy.types.approveflow;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/19 17:19
 */

@Value
public class FlowBusinessKey {

    String businessKey;

    public FlowBusinessKey(String businessKey) {
        if (!checkBusinessKey(businessKey)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("businessKey({})格式错误", businessKey));
        }
        this.businessKey = businessKey;
    }

    private boolean checkBusinessKey(String businessKey) {
        if (StrUtil.isBlank(businessKey)) {
            return false;
        }
        return true;
    }


}
