package com.zhy.types.aliyun;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

import java.util.regex.Matcher;

import static com.zhy.constant.RegexConstant.*;

/**
 * @Author: jobury
 * @Date: 2024/9/11 14:52
 */

@Value
public class OssKey {

    String key;

    public OssKey(String key) {
        if (!checkOssKey(key)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("ossKey({})格式错误", key));
        }
        this.key = key;
    }

    private boolean checkOssKey(String key) {
        boolean ret = true;
        if (StrUtil.isBlank(key)) {
            return false;
        }
        //判断oss key的格式
        Matcher matcher = OSS_KEY_PATTERN.matcher(key);
        if (!matcher.matches()) {
            return false;
        }
        return ret;
    }


}
