package com.zhy.external.remote;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.zhy.exception.ApiException;
import com.zhy.config.SmsClientConfig;
import com.zhy.result.SysCode;
import com.zhy.types.AreaNumber;
import com.zhy.types.Mobile;
import com.zhy.types.AuthCode;
import com.zhy.types.aliyun.SmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.zhy.constant.BizConstant.CHINA_AREA_NUMBER;

@Service
public class AliyunSmsService {

    @Autowired
    private SmsClientConfig client;

    //发送验证码
    public void sendSms(SmsTemplate template, AreaNumber areaNumber, Mobile mobile, Map<String, String> paramMap) {
        String signName = areaNumber.getSmsSignName();
        String templateCode = areaNumber.getAreaNumber().equals(CHINA_AREA_NUMBER) ? template.getCnTemplateCode() : template.getEnTemplateCode();
        String templateParams = null;
        //如果map不为空且至少有一个键值对，把map转换为json字符串，填充进sms模板
        if(CollUtil.isNotEmpty(paramMap)) {
            templateParams = JSONUtil.toJsonStr(paramMap);
        }
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(mobile.getMobile())
                .signName(signName)
                .templateCode(templateCode)
                .templateParam(templateParams)
                .build();
        CompletableFuture<SendSmsResponse> response = client.getSmsAsyncClient().sendSms(sendSmsRequest);
        try {
            SendSmsResponse resp = response.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ApiException(SysCode.ALIYUN_SMS_ERROR, e.getMessage());
        }
    }

    //发送验证码
    public void sendInitialPassword(AreaNumber areaNumber, Mobile mobile, AuthCode code) {
        String signName = areaNumber.getSmsSignName();
        String templateCode = areaNumber.getAreaNumber().equals(CHINA_AREA_NUMBER) ? SmsTemplate.SEND_VERIFICATION.getCnTemplateCode() : SmsTemplate.SEND_VERIFICATION.getEnTemplateCode();
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(mobile.getMobile())
                .signName(signName)
                .templateCode(templateCode)
                .templateParam(StrUtil.format("{\"code\":\"{}\"}", code.getCode()))
                .build();
        CompletableFuture<SendSmsResponse> response = client.getSmsAsyncClient().sendSms(sendSmsRequest);
        try {
            SendSmsResponse resp = response.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ApiException(SysCode.ALIYUN_SMS_ERROR, e.getMessage());
        }
    }




}
