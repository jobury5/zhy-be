package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/20 17:29
 */

@Value
public class Id {

    Long id;

    public Id(Long id){
        if(!checkId(id)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("id({})格式错误", id));
        }
        this.id = id;
    }

    private boolean checkId(Long id) {
        boolean ret = true;
        if(null == id || id < 0L){
            ret = false;
        }
        return ret;
    }

}
