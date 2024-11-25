package com.zhy.types.aliyun;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/21 11:36
 */

@Value
public class OssDirName {

    String dirName;

    public OssDirName(String dirName) {
        if (!checkOssDirName(dirName)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("ossDirName({})格式错误", dirName));
        }
        this.dirName = dirName;
    }

    private boolean checkOssDirName(String dirName) {
        boolean ret = true;
        if (StrUtil.isBlank(dirName)) {
            ret = false;
        }
        return ret;
    }


}