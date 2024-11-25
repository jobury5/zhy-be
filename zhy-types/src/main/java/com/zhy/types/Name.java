package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/20 17:28
 */

@Value
public class Name {

    String name;

    public Name(String name){
        if(!checkName(name)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("name({})格式错误", name));
        }
        this.name = name;
    }

    private boolean checkName(String name) {
        boolean ret = true;
        if(StrUtil.isBlank(name)){
            ret = false;
        }
        return ret;
    }


}
