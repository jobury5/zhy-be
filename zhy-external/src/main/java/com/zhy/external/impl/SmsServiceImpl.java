package com.zhy.external.impl;

import com.zhy.external.SmsService;
import com.zhy.external.remote.AliyunSmsService;
import com.zhy.types.AreaNumber;
import com.zhy.types.AuthCode;
import com.zhy.types.Mobile;
import com.zhy.types.aliyun.SmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private AliyunSmsService aliyunSmsService;

    @Override
    public Boolean sendAuthCode(AreaNumber areaNumber, Mobile mobile, AuthCode code) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", code.getCode());
        aliyunSmsService.sendSms(SmsTemplate.SEND_VERIFICATION, areaNumber, mobile, paramMap);
        return true;
    }

}
