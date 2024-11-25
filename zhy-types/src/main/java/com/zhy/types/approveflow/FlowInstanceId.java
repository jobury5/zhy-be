package com.zhy.types.approveflow;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/19 17:23
 */

@Value
public class FlowInstanceId {

    Long instanceId;

    public FlowInstanceId(Long instanceId) {
        if (!checkInstance(instanceId)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("instanceId({})格式错误", instanceId));
        }
        this.instanceId = instanceId;
    }

    private boolean checkInstance(Long instanceId) {
        if (instanceId == null) {
            return false;
        }
        return true;
    }


}
