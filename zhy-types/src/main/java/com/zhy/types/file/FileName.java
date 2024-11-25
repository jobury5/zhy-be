package com.zhy.types.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/21 10:21
 */
@Value
public class FileName {

    String name;

    public FileName(String name){
        if(!checkName(name)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("fileName({})格式错误", name));
        }
        this.name = name;
    }

    private boolean checkName(String name) {
        boolean ret = true;
        if(StrUtil.isBlank(name)){
            ret = false;
        }
        return ret;
    }

    public FileExtension getFileExtension(){
        String extName = FileUtil.extName(name);
        return new FileExtension(extName);
    }

}
