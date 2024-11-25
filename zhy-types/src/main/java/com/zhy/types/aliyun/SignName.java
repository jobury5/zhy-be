package com.zhy.types.aliyun;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

@Value
public class SignName {


    String signName;

    public SignName(String signName){
        if(!checkSignName(signName)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("signName({})格式错误", signName));
        }
        this.signName = signName;
    }

    private boolean checkSignName(String signName) {
        boolean ret = true;
        if(StrUtil.isBlank(signName)){
            ret = false;
        }
        return ret;
    }

}
