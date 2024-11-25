package com.zhy.types.sys;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/20 14:57
 */

@Value
public class DocType {

    String type;

    public DocType(String type){
        if(!checkDocType(type)){
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("docType({})格式错误", type));
        }
        this.type = type;
    }

    private boolean checkDocType(String docType) {
        boolean ret = true;
        if(StrUtil.isBlank(docType)){
            return false;
        }
        if(!DocTypeEnum.isValid(docType)){
            return false;
        }
        return ret;
    }

}
