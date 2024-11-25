package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/25 16:11
 */
@Value
public class UserType {

    Integer userType;

    public UserType(Integer userType){
        if(!checkUserType(userType)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("userType({})格式错误", userType));
        }
        this.userType = userType;
    }

    private boolean checkUserType(Integer userType) {
        boolean ret = true;
        if(userType == null){
            ret = false;
        }
        return ret;
    }


}
