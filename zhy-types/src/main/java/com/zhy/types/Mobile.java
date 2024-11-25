package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

@Value
public class Mobile {

    String mobile;

    public Mobile(String mobile){
        if(!checkMobile(mobile)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("mobile({})格式错误", mobile));
        }
        this.mobile = mobile;
    }

    private boolean checkMobile(String mobile) {
        boolean ret = true;
        if(StrUtil.isBlank(mobile) || !StrUtil.isNumeric(mobile)){
            return false;
        }
//        Matcher matcher = CHINESE_MOBILE_PATTERN.matcher(mobile);
//        if (!matcher.matches()) {
//            return false;
//        }
        return ret;
    }

}
