package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

import java.util.regex.Matcher;

import static com.zhy.constant.RegexConstant.EMAIL_PATTERN;

/**
 * @Author: jobury
 * @Date: 2024/9/11 18:39
 */

@Value
public class MailAddress {

    String address;

    public MailAddress(String address) {
        if (!checkAddress(address)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("mailAddress({})格式错误", address));
        }
        this.address = address;
    }

    private boolean checkAddress(String address) {
        boolean ret = true;
        if (StrUtil.isBlank(address)) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(address);
        if(!matcher.matches()){
            return false;
        }

        return ret;
    }


}
