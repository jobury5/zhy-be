package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/20 14:51
 */
@Value
public class Version {

    Integer version;

    public Version(Integer version){
        if(!checkVersion(version)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("version({})格式错误", version));
        }
        this.version = version;
    }

    private boolean checkVersion(Integer version) {
        boolean ret = true;
        if(null == version || version < 0){
            ret = false;
        }
        return ret;
    }


}
