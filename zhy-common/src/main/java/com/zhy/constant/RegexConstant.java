package com.zhy.constant;

import java.util.regex.Pattern;

/**
 * @Author: jobury
 * @Date: 2024/9/11 10:01
 */

public class RegexConstant {

    public static final String URL_REGEX =
            "^(https?|ftp)://" + // 协议
                    "(?:(?:[A-Z0-9](?:[A-Z0-9-]{0,61}[A-Z0-9])?\\.)+" + // 域名
                    "[A-Z]{2,6}\\.?|" + // 顶级域名
                    "localhost|" + // 本地主机
                    "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|" + // IP地址
                    "\\[[A-F0-9]*:[A-F0-9:]+\\])" + // IPv6地址
                    "(?::\\d+)?" + // 可选端口
                    "(?:/?|[/?]\\S+)$"; // 路径和查询字符串

    public static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);

    //字符串的最大长度不能超过1023个字符，可以包含字母、数字、下划线 _、连字符 - 以及斜杠 /，并且不能以斜杠 / 开头或结尾。
    public static final String OSS_KEY_REGEX = "^(?![/])((?!/).|/){0,1023}(?<!/)$";
    public static final Pattern OSS_KEY_PATTERN = Pattern.compile(OSS_KEY_REGEX);

    public static final String CHINESE_ID_NUMBER_REGEX = "^([0-9]{17}(X|x))|([0-9]{18})$";
    public static final Pattern CHINESE_ID_NUMBER_PATTERN = Pattern.compile(CHINESE_ID_NUMBER_REGEX);

    public static final String CHINESE_MOBILE_REGEX = "^1[3|4|5|6|7|8|9][0-9]{9}$";
    public static final Pattern CHINESE_MOBILE_PATTERN = Pattern.compile(CHINESE_MOBILE_REGEX);

    public static final String BANK_ACCOUNT_REGEX = "^\\\\d{13,30}$";
    public static final Pattern BANK_ACCOUNT_PATTERN = Pattern.compile(BANK_ACCOUNT_REGEX);

    public static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static final String ENGLISH_REGEX = "^[A-Za-z]+$";

    public static final Pattern ENGLISH_PATTERN = Pattern.compile(ENGLISH_REGEX);

}
