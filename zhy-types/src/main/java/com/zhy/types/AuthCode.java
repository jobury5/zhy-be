package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

@Value
public class AuthCode {

    String code;

    public AuthCode(String code){
        if(!checkAuthCode(code)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("authCode({})格式错误", code));
        }
        this.code = code;
    }

    private boolean checkAuthCode(String authCode) {
        boolean ret = true;
        if(StrUtil.isBlank(authCode) || !StrUtil.isNumeric(authCode) || StrUtil.length(authCode) != 4){
            ret = false;
        }
        return ret;
    }

}
