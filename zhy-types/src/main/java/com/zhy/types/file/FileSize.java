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
public class FileSize {

    Long size;

    public FileSize(Long size){
        if(!checkFileSize(size)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("fileSize({})格式错误", size));
        }
        this.size = size;
    }

    private boolean checkFileSize(Long size) {
        boolean ret = true;
        if(null == size || size <= 0L){
            ret = false;
        }
        return ret;
    }

}
