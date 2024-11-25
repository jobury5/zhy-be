package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import com.zhy.types.aliyun.SmsSignName;
import lombok.Value;

import static com.zhy.constant.BizConstant.CHINA_AREA_NUMBER;

@Value
public class AreaNumber {

    Integer areaNumber;

    public AreaNumber(Integer areaNumber){
        if(!checkAreaNumber(areaNumber)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("areaNumber({})格式错误", areaNumber));
        }
        this.areaNumber = areaNumber;
    }

    private boolean checkAreaNumber(Integer areaNumber) {
        boolean ret = true;
        if(areaNumber == null){
            ret = false;
        }
        return ret;
    }

    public String getSmsSignName(){
        return areaNumber.equals(CHINA_AREA_NUMBER) ? SmsSignName.CHN_SIGN_NAME.getSignName() : SmsSignName.ENG_SIGN_NAME.getSignName();
    }

}
