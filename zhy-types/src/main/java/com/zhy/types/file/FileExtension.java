package com.zhy.types.file;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/21 10:21
 */
@Value
public class FileExtension {

    String extension;

    public FileExtension(String extension){
        if(!checkFileExtension(extension)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("fileExtension({})格式错误", extension));
        }
        this.extension = extension;
    }

    private boolean checkFileExtension(String extension) {
        boolean ret = true;
        if(StrUtil.isBlank(extension)){
            ret = false;
        }
        return ret;
    }

}
