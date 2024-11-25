package com.zhy.types.auth;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/10/8 17:25
 */

@Value
public class BtnPerm {

    String perm;

    public BtnPerm(String perm){
        if(!checkBtnPerm(perm)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("btnPerm({})格式错误", perm));
        }
        this.perm = perm;
    }

    private boolean checkBtnPerm(String perm) {
        boolean ret = true;
        if(StrUtil.isBlank(perm)){
            ret = false;
        }
        return ret;
    }

}
