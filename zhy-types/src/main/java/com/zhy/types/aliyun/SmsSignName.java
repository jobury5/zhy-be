package com.zhy.types.aliyun;

import lombok.Getter;

public enum SmsSignName {

    CHN_SIGN_NAME("智海缘"),
    ENG_SIGN_NAME("Aisailings"),
    ;

    @Getter
    private final String signName;

    SmsSignName(String signName) {
        this.signName = signName;
    }

}
