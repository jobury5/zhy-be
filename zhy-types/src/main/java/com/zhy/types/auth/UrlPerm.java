package com.zhy.types.auth;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/10/8 17:24
 */
@Value
public class UrlPerm {

    String perm;

    public UrlPerm(String perm){
        if(!checkUrlPerm(perm)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("urlPerm({})格式错误", perm));
        }
        this.perm = perm;
    }

    private boolean checkUrlPerm(String perm) {
        boolean ret = true;
        if(StrUtil.isBlank(perm)){
            ret = false;
        }
        return ret;
    }


}
