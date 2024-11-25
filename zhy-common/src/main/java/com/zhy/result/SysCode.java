package com.zhy.result;

public enum SysCode implements StatusCode{

    SUCCESS(1000, "请求成功"),
    FAILED(1001, "请求失败"),
    PARAM_VALIDATE_ERROR(1002,"参数校验失败"),

    DATA_VALIDATE_ERROR(1003,"数据校验失败"),
    RESPONSE_PACK_ERROR(1005,"response返回包装失败"),
    RPC_ERROR(1100,"外部接口调用错误"),
    ALIYUN_OSS_ERROR(1110,"阿里云OSS接口调用错误"),
    ALIYUN_OCR_ERROR(1120,"阿里云OCR接口调用错误"),
    ALIYUN_AUTOML_OCR_ERROR(1121,"阿里云OCR文档智能接口调用错误"),
    ALIYUN_OCR_IDENTITY_ERROR(1122,"阿里云OCR身份证识别接口调用错误"),
    ALIYUN_OCR_PASSPORT_ERROR(1123,"阿里云OCR护照识别接口调用错误"),
    ALIYUN_OCR_INVOICE_ERROR(1124,"阿里云OCR发票识别接口调用错误"),
    ALIYUN_SMS_ERROR(1130,"阿里云SMS接口调用错误"),
    SMTP_ERROR(1200, "发送邮件服务器错误"),
    ACCESS_TOKEN_NOT_EXISTS(1400, "ACCESS TOKEN不存在"),
    ACCESS_TOKEN_EXPIRED(1401, "ACCESS TOKEN已经过期"),
    ACCESS_TOKEN_NOT_VALID(1402, "ACCESS TOKEN无效"),
    ACCESS_TOKEN_REQUIRED(1403, "ACCESS TOKEN不能为空"),
    REFRESH_TOKEN_EXPIRED(1404, "REFRESH TOKEN已经过期"),
    ;


    private int code;
    private String msg;

    SysCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }



}
