package com.zhy.types.approveflow;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/19 17:16
 */

@Value
public class FlowProcessUniqueId {

    Long processId;

    String processKey;

    public FlowProcessUniqueId(FlowProcessEnum flowProcessEnum) {
        if (!checkProcess(flowProcessEnum.getId(), flowProcessEnum.getKey())) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("processId({})或者processKey({})格式错误", flowProcessEnum.getId(), flowProcessEnum.getKey()));
        }
        this.processId = flowProcessEnum.getId();
        this.processKey = flowProcessEnum.getKey();
    }

    private boolean checkProcess(Long processId, String processKey) {
//        if (null == processId && StrUtil.isBlank(processKey)) {
//            return false;
//        }
        return true;
    }

}
