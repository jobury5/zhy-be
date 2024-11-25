package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

@Value
public class UserName {

    String userName;

    public UserName(String userName){
        if(!checkUserName(userName)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("userName({})格式错误", userName));
        }
        this.userName = userName;
    }

    private boolean checkUserName(String userName) {
        boolean ret = true;
        if(StrUtil.isBlank(userName)){
            ret = false;
        }
        return ret;
    }


}
