package com.zhy.types.sys;

import lombok.Getter;

public enum DocTypeEnum {

    WORD("word", "docx"),
    EXCEL("excel", "xlsx"),
    TXT("txt", "txt"),
    ;

    @Getter
    private final String type;

    @Getter
    private final String extension;

    DocTypeEnum(String type, String extension) {
        this.type = type;
        this.extension = extension;
    }

    public static boolean isValid(String docType) {
        boolean ret = false;
        for(DocTypeEnum docTypeEnum : DocTypeEnum.values()){
            if(docTypeEnum.getType().equalsIgnoreCase(docType)){
                return true;
            }
        }
        return ret;
    }

}
