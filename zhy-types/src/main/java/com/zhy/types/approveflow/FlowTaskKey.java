package com.zhy.types.approveflow;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/9 17:01
 */

@Value
public class FlowTaskKey {

    String taskKey;

    public FlowTaskKey(String taskKey) {
        if (!checkTaskKey(taskKey)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("taskKey({})格式错误", taskKey));
        }
        this.taskKey = taskKey;
    }
    private boolean checkTaskKey(String taskKey) {
        if (StrUtil.isBlank(taskKey)) {
            return false;
        }
        return true;
    }

}
