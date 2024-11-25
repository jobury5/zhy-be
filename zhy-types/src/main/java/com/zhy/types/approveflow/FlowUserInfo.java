package com.zhy.types.approveflow;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/19 17:21
 */

@Value
public class FlowUserInfo {

    String id;

    String name;

    public FlowUserInfo(String id, String name) {
        if (!checkUser(id, name)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("id({})或者name({})格式错误", id, name));
        }
        this.id = id;
        this.name = name;
    }

    private boolean checkUser(String id, String name) {
        if(StrUtil.isBlank(id) || StrUtil.isBlank(name)){
            return false;
        }
        return true;
    }


}
