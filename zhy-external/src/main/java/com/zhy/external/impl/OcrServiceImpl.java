package com.zhy.external.impl;

import com.zhy.domain.entity.ocr.OcrCert;
import com.zhy.domain.entity.ocr.OcrIdentity;
import com.zhy.domain.entity.ocr.OcrInvoice;
import com.zhy.domain.entity.ocr.OcrPassport;
import com.zhy.external.OcrService;
import com.zhy.external.OssService;
import com.zhy.external.remote.AliyunOcrService;
import com.zhy.types.Url;
import com.zhy.types.aliyun.OcrTemplate;
import com.zhy.types.aliyun.OssKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/13 10:54
 */

@Service
public class OcrServiceImpl implements OcrService {

    @Autowired
    private AliyunOcrService aliyunOcrService;

    @Autowired
    private OssService ossService;

    @Override
    public OcrIdentity recognizeIdentity(OssKey ossKey) {
        Url signedUrl = ossService.getSignedUrl(ossKey);
        return aliyunOcrService.recognizeIdentity(signedUrl);
    }

    @Override
    public OcrPassport recognizePassport(OssKey ossKey) {
        Url signedUrl = ossService.getSignedUrl(ossKey);
        return aliyunOcrService.recognizePassport(signedUrl);
    }

    @Override
    public List<OcrInvoice> recognizeInvoices(OssKey ossKey) {
        Url signedUrl = ossService.getSignedUrl(ossKey);
        return aliyunOcrService.recognizeInvoices(signedUrl);
    }

    @Override
    public OcrCert recognizeCert(OcrTemplate ocrTemplate, OssKey ossKey) {
        Url signedUrl = ossService.getSignedUrl(ossKey);
        return aliyunOcrService.recognizeCert(ocrTemplate, signedUrl);
    }

}
