package com.zhy.domain.entity;

import com.zhy.types.aliyun.OssKey;
import com.zhy.types.file.FileContentType;
import com.zhy.types.file.FileSize;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/21 21:01
 */

@Data
public class OssObject {

    private OssKey key;

    private FileSize size;

    private FileContentType contentType;

    private String md5;

}
