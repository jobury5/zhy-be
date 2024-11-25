package com.zhy.domain.entity;

import cn.hutool.core.util.StrUtil;
import com.zhy.types.Id;
import com.zhy.types.aliyun.OssDirName;
import com.zhy.types.aliyun.OssPath;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/21 11:29
 */

@Data
public class AttachType {

    private Id attachTypeId;

    private OssDirName type;

    private OssDirName subType;

    public OssPath getOssPath(){
        String path = StrUtil.concat(true,  type.getDirName(), "/", subType.getDirName());
        return new OssPath(path);
    }

}
