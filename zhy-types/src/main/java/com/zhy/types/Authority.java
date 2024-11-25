package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/21 14:46
 */

@Value
public class Authority {

    String authority;

    public Authority(String authority) {
        if (!checkAuthority(authority)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("authority({})格式错误", authority));
        }
        this.authority = authority;
    }

    private boolean checkAuthority(String authority) {
        boolean ret = true;
        if (StrUtil.isBlank(authority)) {
            ret = false;
        }
        return ret;
    }

}
