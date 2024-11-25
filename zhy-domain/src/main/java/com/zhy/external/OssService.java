package com.zhy.external;

import com.zhy.domain.entity.OssObject;
import com.zhy.types.Url;
import com.zhy.types.aliyun.OssKey;

import java.io.InputStream;

public interface OssService {

    //获取签名后的下载地址
    Url getSignedUrl(OssKey ossKey);

    //获取签名图片下载地址，可按比例调整图片大小
    Url getSignedImgUrlWithResize(OssKey ossKey, Integer width, Integer height);

    //上传附件
    OssObject upload(OssKey ossKey, InputStream inputStream);

}
