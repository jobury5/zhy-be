package com.zhy.types.auth;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/24 19:03
 */

@Value
public class Token {

    String token;

    public Token(String token){
        if(!checkToken(token)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("token({})格式错误", token));
        }
        this.token = token;
    }

    private boolean checkToken(String token) {
        boolean ret = true;
        if(StrUtil.isBlank(token)){
            ret = false;
        }
        return ret;
    }


}
