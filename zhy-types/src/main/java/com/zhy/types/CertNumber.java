package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/8/21 14:46
 */

@Value
public class CertNumber {

    String certNumber;

    public CertNumber(String certNumber) {
        if (!checkCertNumber(certNumber)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("certNumber({})格式错误", certNumber));
        }
        this.certNumber = certNumber;
    }

    private boolean checkCertNumber(String certNumber) {
        boolean ret = true;
        if (StrUtil.isBlank(certNumber)) {
            ret = false;
        }
        return ret;
    }

}
