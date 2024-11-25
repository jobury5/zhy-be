package com.zhy.types;


import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

@Value
public class Password {

    String password;

    public Password(String password){
        if(!checkPassword(password)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("password({})格式错误", password));
        }
        this.password = password;
    }

    private boolean checkPassword(String password) {
        boolean ret = true;
        if(StrUtil.isBlank(password)){
            ret = false;
        }
        return ret;
    }


}
