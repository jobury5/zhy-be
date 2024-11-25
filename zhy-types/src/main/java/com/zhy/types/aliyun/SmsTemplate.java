package com.zhy.types.aliyun;

import lombok.Getter;

public enum SmsTemplate {

    SEND_VERIFICATION("SMS_465373036", "SMS_465313021"),
    SEND_INITIAL_PASSWORD("SMS_467480323", "SMS_467540311"),
    CERT_EXPIRE_WARNING("SMS_468580248", "SMS_468590240"),
    CERT_EXPIRED_WARNING("SMS_468570256", "SMS_468570275"),
    ACCOUNT_CANCEL("SMS_469045164", "SMS_468870175"),
    ;


    @Getter
    private final String cnTemplateCode;

    @Getter
    private final String enTemplateCode;

    SmsTemplate(String cnTemplateCode, String enTemplateCode) {
        this.cnTemplateCode = cnTemplateCode;
        this.enTemplateCode = enTemplateCode;
    }

}
