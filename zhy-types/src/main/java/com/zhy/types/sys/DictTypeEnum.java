package com.zhy.types.sys;

import lombok.Getter;

public enum DictTypeEnum {

    NATIONALITY("nationality"),
    CERT_TYPE("cert_type"),
    TEMPLATE_TYPE("template_type"),
    ;

    @Getter
    private final String dictType;

    DictTypeEnum(String dictType) {
        this.dictType = dictType;
    }

}
