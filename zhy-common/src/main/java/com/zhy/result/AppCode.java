package com.zhy.result;

public enum AppCode implements StatusCode {
    APP_ERROR(2000, "业务异常"),
    RECRUIT_ERROR(3000, "招聘模块异常"),
    RECRUIT_RESUME_ERROR(3100, "招聘-船员简历异常"),
    DISPATCH_ERROR(4000, "派遣模块异常"),
    DISPATCH_MSA_ERROR(4100, "派遣-MSA异常"),
    DISPATCH_TRAVEL_ERROR(4200, "派遣-差旅异常"),
    CERT_ERROR(5000, "证书模块异常"),
    INSUR_ERROR(5100, "保险模块异常"),
    FINANCE_ERROR(5200, "财务模块异常"),
    TRAIN_ERROR(5300, "培训模块异常"),
    SHIPOWNER_ERROR(6000, "船东模块异常"),
    SHIPOWNER_SHIP_ERROR(6100, "船东-船舶模块异常"),
    CONFIG_ERROR(8000, "系统设置模块异常"),
    AUTH_ERROR(9000, "用户和权限模块异常"),
    AUTH_URL_PERMISSION_FORBID(9001, "没有URL的访问权限"),
    USER_NAME_NOT_EXIST(9100, "用户名不存在"),
    PASSWORD_WRONG(9101, "密码错误"),
    MOBILE_NOT_EXIST(9102, "手机号码不存在"),
    AUTH_CODE_WRONG_OR_EXPIRED(9103, "验证码错误或者已经过期"),
    ;

    private int code;
    private String msg;

    AppCode(int code, String msg) {
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
