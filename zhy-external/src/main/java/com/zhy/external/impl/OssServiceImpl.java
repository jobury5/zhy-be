package com.zhy.external.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.zhy.domain.entity.OssObject;
import com.zhy.exception.ApiException;
import com.zhy.external.OssService;
import com.zhy.config.OssClientConfig;
import com.zhy.config.prop.AliyunProperties;
import com.zhy.result.SysCode;
import com.zhy.types.Url;
import com.zhy.types.aliyun.OssKey;
import com.zhy.types.file.FileContentType;
import com.zhy.types.file.FileSize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Optional;

/**
 * @Author: jobury
 * @Date: 2024/9/10 19:04
 */

@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private AliyunProperties aliyunProperties;

    @Autowired
    private OssClientConfig ossClientConfig;

    @Override
    public Url getSignedUrl(OssKey ossKey) {
        Long expire = aliyunProperties.getOssExpiration();
        Date expiration = new Date(new Date().getTime() + expire);
        // 生成签名URL。
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(aliyunProperties.getOssBucket(), ossKey.getKey(), HttpMethod.GET);
        // 设置过期时间。
        request.setExpiration(expiration);
        URL signedUrl = null;
        OSS ossClient = ossClientConfig.getOssClientExternal();
        try {
            // 通过HTTP GET请求生成签名URL。
            signedUrl = ossClient.generatePresignedUrl(request);
        } catch (OSSException | ClientException e) {
            throw new ApiException(SysCode.ALIYUN_OSS_ERROR, e.getMessage());
        }
        return Optional.ofNullable(signedUrl).map(u -> new Url(u.toString())).orElse(null);
    }

    @Override
    public Url getSignedImgUrlWithResize(OssKey ossKey, Integer width, Integer height) {
        String style = StrUtil.format("image/resize,lfit,w_{},h_{}", width, height);
        Long expire = aliyunProperties.getOssExpiration();
        Date expiration = new Date(new Date().getTime() + expire);
        // 生成签名URL。
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(aliyunProperties.getOssBucket(), ossKey.getKey(), HttpMethod.GET);
        // 设置过期时间。
        request.setExpiration(expiration);
        request.setProcess(style);
        URL signedUrl = null;
        OSS ossClient = ossClientConfig.getOssClientExternal();
        try {
            // 通过HTTP GET请求生成签名URL。
            signedUrl = ossClient.generatePresignedUrl(request);
        } catch (OSSException | ClientException e) {
            throw new ApiException(SysCode.ALIYUN_OSS_ERROR, e.getMessage());
        }
        return Optional.ofNullable(signedUrl).map(u -> new Url(u.toString())).orElse(null);
    }

    @Override
    public OssObject upload(OssKey ossKey, InputStream inputStream) {
        OSS ossClient = ossClientConfig.getOssClient();
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(aliyunProperties.getOssBucket(), ossKey.getKey(), inputStream);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            log.info("Success put object into OSS, target bucket: {}, target object key:{}. ", aliyunProperties.getOssBucket(), ossKey.getKey());
            // 获取文件元数据
            ObjectMetadata objectMetadata = ossClient.getObjectMetadata(aliyunProperties.getOssBucket(), ossKey.getKey());
            OssObject ossObject = new OssObject();
            ossObject.setKey(ossKey);
            ossObject.setSize(new FileSize(objectMetadata.getContentLength()));
            ossObject.setContentType(new FileContentType(objectMetadata.getContentType()));
            ossObject.setMd5(objectMetadata.getContentMD5());
            return ossObject;
        } catch (OSSException | ClientException e) {
            throw new ApiException(SysCode.ALIYUN_OSS_ERROR, e.getMessage());
        }
    }

}
