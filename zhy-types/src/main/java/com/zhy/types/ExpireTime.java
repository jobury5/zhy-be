package com.zhy.types;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * @Author: jobury
 * @Date: 2024/9/24 19:05
 */

@Value
public class ExpireTime {

    LocalDateTime expireTime;

    public ExpireTime(LocalDateTime expireTime) {
        if (!checkExpireTime(expireTime)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("expireTime({})格式错误或者已经过期", expireTime));
        }
        this.expireTime = expireTime;
    }

    private boolean checkExpireTime(LocalDateTime expireTime) {
        boolean ret = true;
        //if (expireTime == null || LocalDateTimeUtil.now().isAfter(expireTime)) {
        if (expireTime == null) {
            ret = false;
        }
        return ret;
    }

    public boolean isExpired() {
        return LocalDateTimeUtil.now().isAfter(expireTime);
    }

}
