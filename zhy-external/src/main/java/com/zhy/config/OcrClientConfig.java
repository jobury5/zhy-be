package com.zhy.config;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.teaopenapi.models.Config;
import com.zhy.exception.ApiException;
import com.zhy.config.prop.AliyunProperties;
import com.zhy.result.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: jobury
 * @Date: 2024/9/12 18:07
 */

@Component
public class OcrClientConfig {

    @Autowired
    private AliyunProperties aliyunProperties;
    private static final String ENDPOINT = "ocr-api.cn-hangzhou.aliyuncs.com";
    private static final String AUTOML_ENDPOINT = "documentautoml.cn-beijing.aliyuncs.com";
    private volatile Client ocrClient;
    private volatile com.aliyun.documentautoml20221229.Client ocrAutoMlClient;

    public Client getOcrClient() {
        if (ocrClient == null) {
            synchronized (OcrClientConfig.class) {
                if (ocrClient == null) {
                    Config config = new Config();
                    config.setAccessKeyId(aliyunProperties.getAccessKey());
                    config.setAccessKeySecret(aliyunProperties.getAccessSecret());
                    // Endpoint 请参考 https://api.aliyun.com/product/ocr-api
                    config.setEndpoint(ENDPOINT);
                    try {
                        ocrClient = new Client(config);
                    } catch (Exception e) {
                        throw new ApiException(SysCode.ALIYUN_OCR_ERROR, e.getMessage());
                    }
                }
            }
        }
        return ocrClient;
    }


    public com.aliyun.documentautoml20221229.Client getOcrAutoMlClient() {
        if (ocrAutoMlClient == null) {
            synchronized (OcrClientConfig.class) {
                if (ocrAutoMlClient == null) {
                    Config config = new Config();
                    config.setAccessKeyId(aliyunProperties.getAccessKey());
                    config.setAccessKeySecret(aliyunProperties.getAccessSecret());
                    // Endpoint 请参考 https://api.aliyun.com/product/documentAutoml
                    config.setEndpoint(AUTOML_ENDPOINT);
                    try {
                        ocrAutoMlClient = new com.aliyun.documentautoml20221229.Client(config);
                    } catch (Exception e) {
                        throw new ApiException(SysCode.ALIYUN_AUTOML_OCR_ERROR, e.getMessage());
                    }
                }
            }
        }
        return ocrAutoMlClient;
    }



}
