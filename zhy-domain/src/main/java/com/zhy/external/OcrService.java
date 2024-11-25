package com.zhy.external;

import com.zhy.domain.entity.ocr.OcrCert;
import com.zhy.domain.entity.ocr.OcrIdentity;
import com.zhy.domain.entity.ocr.OcrInvoice;
import com.zhy.domain.entity.ocr.OcrPassport;
import com.zhy.types.aliyun.OcrTemplate;
import com.zhy.types.aliyun.OssKey;

import java.util.List;

public interface OcrService {
    //识别身份证
    OcrIdentity recognizeIdentity(OssKey ossKey);

    //识别中国护照
    OcrPassport recognizePassport(OssKey ossKey);

    //识别混贴发票
    List<OcrInvoice> recognizeInvoices(OssKey ossKey);

    //识别自定义证书
    OcrCert recognizeCert(OcrTemplate ocrTemplate, OssKey ossKey);
}
