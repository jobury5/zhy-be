package com.zhy.external.remote;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.documentautoml20221229.models.PredictTemplateModelRequest;
import com.aliyun.documentautoml20221229.models.PredictTemplateModelResponse;
import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.*;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import com.zhy.constant.BizConstant;
import com.zhy.constant.SysConstant;
import com.zhy.domain.entity.ocr.*;
import com.zhy.exception.ApiException;
import com.zhy.config.OcrClientConfig;
import com.zhy.util.OcrParseUtil;
import com.zhy.result.SysCode;
import com.zhy.types.Url;
import com.zhy.types.aliyun.OcrTemplate;
import com.zhy.util.DocUtil;
import com.zhy.util.HttpUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zhy.constant.BizConstant.STATUS_CODE_SUCCESS;

/**
 * @Author: jobury
 * @Date: 2024/9/11 17:53
 */

@Service
public class AliyunOcrService {

    @Autowired
    private OcrClientConfig ocrClientConfig;

    public OcrIdentity recognizeIdentity(Url url) {
        Client ocrClient = ocrClientConfig.getOcrClient();
        RecognizeIdcardRequest recognizeIdcardRequest = new RecognizeIdcardRequest();
        recognizeIdcardRequest.setUrl(url.getUrl());
        //如果是pdf文件，则需要将pdf转换为jpg格式
        if (HttpUrlUtil.getFileExtensionFromURL(url.getUrl()).equalsIgnoreCase(SysConstant.FileType.PDF)) {
            InputStream inputStream = DocUtil.convertToJpgFromPdfUrl(url.getUrl(), 0);
            if (inputStream != null) {
                recognizeIdcardRequest.setBody(inputStream);
            }
        }
        RuntimeOptions runtime = new RuntimeOptions();
        OcrIdentity ocrIdentity = null;
        try {
            RecognizeIdcardResponse resp = ocrClient.recognizeIdcardWithOptions(recognizeIdcardRequest, runtime);
            String jsonString = Common.toJSONString(resp);
            JSONObject jsonObject = (JSONObject) JSON.parse(jsonString);
            Integer code = jsonObject.getInteger("statusCode");
            if (code.equals(STATUS_CODE_SUCCESS)) {
                JSONObject body = (JSONObject) jsonObject.get("body");
                String jsonDateStr = body.getString("data");
                JSONObject data = (JSONObject) JSON.parse(jsonDateStr);
                if (data != null) {
                    JSONObject identityData = data.getJSONObject("data");
                    JSONObject face = identityData.getJSONObject("face");
                    JSONObject realData = face.getJSONObject("data");
                    if (realData != null) {
                        ocrIdentity = new OcrIdentity();
                        ocrIdentity.setIdNumber(realData.getString("idNumber"));
                        ocrIdentity.setName(realData.getString("name"));
                        ocrIdentity.setSex(realData.getString("sex"));
                        ocrIdentity.setEthnicity(realData.getString("ethnicity"));
                        ocrIdentity.setAddress(realData.getString("address"));
                        try {
                            ocrIdentity.setBirthDate(LocalDateTimeUtil.parseDate(realData.getString("birthDate"), "yyyy年M月d日"));
                        } catch (Exception ignored) {
                        }
                    }
                }
            } else {
                throw new ApiException(SysCode.ALIYUN_OCR_IDENTITY_ERROR, StrUtil.format("返回码:{}", code));
            }
        } catch (Exception e) {
            throw new ApiException(SysCode.ALIYUN_OCR_IDENTITY_ERROR, e.getMessage());
        }
        return ocrIdentity;
    }

    public OcrPassport recognizePassport(Url url) {
        Client ocrClient = ocrClientConfig.getOcrClient();
        RecognizeChinesePassportRequest recognizeChinesePassportRequest = new RecognizeChinesePassportRequest();
        recognizeChinesePassportRequest.setUrl(url.getUrl());
        //如果是pdf文件，则需要将pdf转换为jpg格式
        if (HttpUrlUtil.getFileExtensionFromURL(url.getUrl()).equalsIgnoreCase(SysConstant.FileType.PDF)) {
            InputStream inputStream = DocUtil.convertToJpgFromPdfUrl(url.getUrl(), 0);
            if (inputStream != null) {
                recognizeChinesePassportRequest.setBody(inputStream);
            }
        }
        RuntimeOptions runtime = new RuntimeOptions();
        OcrPassport ocrPassport = null;
        try {
            RecognizeChinesePassportResponse resp = ocrClient.recognizeChinesePassportWithOptions(recognizeChinesePassportRequest, runtime);
            String jsonString = Common.toJSONString(resp);
            JSONObject jsonObject = (JSONObject) JSON.parse(jsonString);
            Integer code = jsonObject.getInteger("statusCode");
            if (code.equals(STATUS_CODE_SUCCESS)) {
                JSONObject body = (JSONObject) jsonObject.get("body");
                String jsonDateStr = body.getString("data");
                JSONObject data = (JSONObject) JSON.parse(jsonDateStr);
                if (data != null) {
                    JSONObject realData = data.getJSONObject("data");
                    if (realData != null) {
                        ocrPassport = new OcrPassport();
                        ocrPassport.setPassportType(realData.getString("passportType"));
                        ocrPassport.setCountryCode(realData.getString("countryCode"));
                        ocrPassport.setPassportNumber(realData.getString("passportNumber"));
                        ocrPassport.setName(realData.getString("name"));
                        ocrPassport.setNameEn(realData.getString("nameEn"));
                        ocrPassport.setSex(realData.getString("sex"));
                        ocrPassport.setBirthPlace(realData.getString("birthPlace"));
                        ocrPassport.setNationality(realData.getString("nationality"));
                        ocrPassport.setIssuePlace(realData.getString("issuePlace"));
                        ocrPassport.setIssueAuthority(realData.getString("issueAuthority"));
                        try {
                            ocrPassport.setValidToDate(LocalDateTimeUtil.parseDate(realData.getString("validToDate"), "yyyy.MM.dd"));
                        } catch (Exception ignored) {
                        }
                        try {
                            ocrPassport.setBirthDate(LocalDateTimeUtil.parseDate(realData.getString("birthDate"), "yyyy.MM.dd"));
                        } catch (Exception ignored) {
                        }
                        try {
                            ocrPassport.setIssueDate(LocalDateTimeUtil.parseDate(realData.getString("issueDate"), "yyyy.MM.dd"));
                        } catch (Exception ignored) {
                        }
                    }
                }
            } else {
                throw new ApiException(SysCode.ALIYUN_OCR_PASSPORT_ERROR, StrUtil.format("返回码:{}", code));
            }
        } catch (Exception e) {
            throw new ApiException(SysCode.ALIYUN_OCR_PASSPORT_ERROR, e.getMessage());
        }
        return ocrPassport;
    }

    //识别发票，支持pdf或者图片格式，支持pdf分页识别
    public List<OcrInvoice> recognizeInvoices(Url url) {
        Client ocrClient = ocrClientConfig.getOcrClient();
        RecognizeMixedInvoicesRequest recognizeMixedInvoicesRequest = new RecognizeMixedInvoicesRequest();
        recognizeMixedInvoicesRequest.setUrl(url.getUrl());
        recognizeMixedInvoicesRequest.setPageNo(1);
        RuntimeOptions runtime = new RuntimeOptions();
        List<OcrInvoice> invoiceList = new ArrayList<>();
        try {
            RecognizeMixedInvoicesResponse resp = ocrClient.recognizeMixedInvoicesWithOptions(recognizeMixedInvoicesRequest, runtime);
            String jsonString = Common.toJSONString(resp);
            OcrParseUtil.parseInvoiceJson(invoiceList, jsonString);
        } catch (Exception e) {
            throw new ApiException(SysCode.ALIYUN_OCR_INVOICE_ERROR, e.getMessage());
        }
        //如果是pdf文件，则需要获取pdf的页数
        if (HttpUrlUtil.getFileExtensionFromURL(url.getUrl()).equalsIgnoreCase(SysConstant.FileType.PDF)) {
            int page = DocUtil.countPdfFromPdfUrl(url.getUrl());
            if (page > 1) {
                for (int i = 0; i < page - 1; i++) {
                    //设置pdf页数（从2开始）
                    recognizeMixedInvoicesRequest.setPageNo(i + 2);
                    runtime = new RuntimeOptions();
                    try {
                        RecognizeMixedInvoicesResponse resp = ocrClient.recognizeMixedInvoicesWithOptions(recognizeMixedInvoicesRequest, runtime);
                        String jsonString = Common.toJSONString(resp);
                        OcrParseUtil.parseInvoiceJson(invoiceList, jsonString);
                    } catch (Exception e) {
                        //第2页以后出现异常，不处理
                        //throw new ApiException(SysCode.ALIYUN_OCR_INVOICE_ERROR, e.getMessage());
                    }
                }
            }
        }
        return invoiceList;
    }

    public OcrCert recognizeCert(OcrTemplate ocrTemplate, Url url) {
        OcrCert ocrCert = new OcrCert();
        List<OcrCertItem> ocrCertItemList = new ArrayList<>();
        com.aliyun.documentautoml20221229.Client client = ocrClientConfig.getOcrAutoMlClient();
        PredictTemplateModelRequest predictTemplateModelRequest = new PredictTemplateModelRequest()
                .setContent(url.getUrl())
                .setBinaryToText(false)
                .setTaskId(ocrTemplate.getTemplateId());
        RuntimeOptions runtime = new RuntimeOptions();
        PredictTemplateModelResponse resp = null;
        try {
            resp = client.predictTemplateModelWithOptions(predictTemplateModelRequest, runtime);
        } catch (Exception e) {
            throw new ApiException(SysCode.ALIYUN_AUTOML_OCR_ERROR, e.getMessage());
        }
        String jsonString = Common.toJSONString(resp);
        OcrParseUtil.parseCertData(jsonString, ocrCert, ocrCertItemList, true);
        //是否有第2页的模板id
        if (null != ocrTemplate.getSecondTemplateId()) {
            //计算pdf页数
            int i = DocUtil.countPdfFromPdfUrl(url.getUrl());
            if (i > 1) {
                //存在第2页，则把第2页转换为jpg
                InputStream inputStream = DocUtil.convertToJpgFromPdfUrl(url.getUrl(), 1);
                if (inputStream != null) {
                    String imgBase64 = DocUtil.convertImageToBase64(inputStream);
                    if (StrUtil.isNotBlank(imgBase64)) {
                        predictTemplateModelRequest = new PredictTemplateModelRequest()
                                //.setContent(url.getUrl())
                                .setBinaryToText(true)
                                //图片 base64 编码内容
                                .setBody(imgBase64)
                                .setTaskId(ocrTemplate.getSecondTemplateId());
                        //runtime = new RuntimeOptions();
                        PredictTemplateModelResponse resp2 = null;
                        try {
                            resp2 = client.predictTemplateModelWithOptions(predictTemplateModelRequest, runtime);
                            String jsonString2 = Common.toJSONString(resp2);
                            OcrParseUtil.parseCertData(jsonString2, ocrCert, ocrCertItemList, ocrTemplate == OcrTemplate.COC);
                        } catch (Exception e) {
                            //第2页不抛异常
                            //throw new ApiException(SysCode.ALIYUN_AUTOML_OCR_ERROR, e.getMessage());
                        }

                    }
                }

            }
        }
        //最后处理stcw明细列表，如果不为空，则setOcrCertItems
        //清洗数据，如果没有prefix，则移除record
        if (CollUtil.isNotEmpty(ocrCertItemList)) {
            ocrCertItemList = ocrCertItemList.stream().filter(i -> (StrUtil.isNotBlank(i.getPrefix()) || StrUtil.isNotBlank(i.getTitle())) && !BizConstant.STCW_PREFIX_EMPTY.equalsIgnoreCase(i.getPrefix())).collect(Collectors.toList());
            ocrCert.setOcrCertItems(ocrCertItemList);
        }
        return ocrCert;
    }


}
