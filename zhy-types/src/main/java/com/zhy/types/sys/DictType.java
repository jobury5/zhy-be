package com.zhy.types.sys;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/18 16:30
 */

@Value
public class DictType {

    String type;

    public DictType(String type){
        if(!checkDictType(type)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("dictType({})格式错误", type));
        }
        this.type = type;
    }

    private boolean checkDictType(String type) {
        boolean ret = true;
        if(StrUtil.isBlank(type)){
            ret = false;
        }
        return ret;
    }

}
