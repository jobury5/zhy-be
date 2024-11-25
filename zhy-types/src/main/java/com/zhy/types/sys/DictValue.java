package com.zhy.types.sys;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/18 16:33
 */
@Value
public class DictValue {

    String value;

    public DictValue(String value){
        if(!checkDictValue(value)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("dictValue({})格式错误", value));
        }
        this.value = value;
    }

    private boolean checkDictValue(String value) {
        boolean ret = true;
        if(StrUtil.isBlank(value) || StrUtil.length(value) > 20){
            ret = false;
        }
        return ret;
    }

}
