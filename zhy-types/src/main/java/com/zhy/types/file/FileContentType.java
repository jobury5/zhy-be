package com.zhy.types.file;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/21 10:20
 */

@Value
public class FileContentType {

    String contentType;

    public FileContentType(String contentType){
        if(!checkFileContentType(contentType)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("fileContentType({})格式错误", contentType));
        }
        this.contentType = contentType;
    }

    private boolean checkFileContentType(String contentType) {
        boolean ret = true;
        if(StrUtil.isBlank(contentType)){
            ret = false;
        }
        return ret;
    }

}
