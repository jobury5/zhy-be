package com.zhy.types.aliyun;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/21 10:16
 */

@Value
public class OssFileName {

    String fileName;

    public OssFileName(String fileName) {
        if (!checkFileName(fileName)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("attachFileName({})格式错误", fileName));
        }
        this.fileName = fileName;
    }

    private boolean checkFileName(String fileName) {
        boolean ret = true;
        if (StrUtil.isBlank(fileName)) {
            ret = false;
        }
        return ret;
    }


}
