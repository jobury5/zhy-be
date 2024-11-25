package com.zhy.types.aliyun;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/21 20:27
 */

@Value
public class OssPath {

    String path;

    public OssPath(String path) {
        if (!checkOssPath(path)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("ossPath({})格式错误", path));
        }
        this.path = path;
    }

    private boolean checkOssPath(String path) {
        boolean ret = true;
        if (StrUtil.isBlank(path)) {
            ret = false;
        }
        return ret;
    }


}
