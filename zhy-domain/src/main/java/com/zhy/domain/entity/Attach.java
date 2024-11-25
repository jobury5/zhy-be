package com.zhy.domain.entity;

import cn.hutool.core.util.StrUtil;
import com.zhy.types.Id;
import com.zhy.types.aliyun.OssFileName;
import com.zhy.types.aliyun.OssKey;
import com.zhy.types.aliyun.OssPath;
import com.zhy.types.file.FileContentType;
import com.zhy.types.file.FileExtension;
import com.zhy.types.file.FileName;
import com.zhy.types.file.FileSize;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/21 10:00
 */

@Data
public class Attach {

    private Id attachId;

    private AttachType attachType;

    private OssFileName fileName;

    private FileSize fileSize;

    private FileName originalFileName;

    private FileContentType contentType;

    private FileExtension fileExtension;

    private String md5;

    public OssKey getOssKey(){
        OssPath ossPath = attachType.getOssPath();
        String ossFileName = fileName.getFileName();
        String year = StrUtil.sub(ossFileName, 0, 4);
        String month = StrUtil.sub(ossFileName, 4, 6);
        String ossKey = StrUtil.concat(true, ossPath.getPath(),"/",year,"/",month,"/",ossFileName);
        return new OssKey(ossKey);
    }

}
