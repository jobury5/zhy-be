package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

@Value
public class UserId {

    Long userId;

    public UserId(Long userId){
        if(!checkUserId(userId)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("userId({})格式错误", userId));
        }
        this.userId = userId;
    }

    private boolean checkUserId(Long userId) {
        boolean ret = true;
        if(null == userId || userId <= 0L){
            ret = false;
        }
        return ret;
    }


}
