package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/18 18:04
 */

@Value
public class Code {

    String code;

    public Code(String code){
        if(!checkCode(code)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("code({})格式错误", code));
        }
        this.code = code;
    }

    private boolean checkCode(String code) {
        boolean ret = true;
        if(StrUtil.isBlank(code)){
            ret = false;
        }
        return ret;
    }

}
