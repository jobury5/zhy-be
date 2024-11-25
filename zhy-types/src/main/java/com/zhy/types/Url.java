package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

import java.util.regex.Matcher;

import static com.zhy.constant.RegexConstant.URL_PATTERN;

/**
 * @Author: jobury
 * @Date: 2024/9/11 09:41
 */
@Value
public class Url {

    private String url;

    public Url(String url) {
        if (!checkUrl(url)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("url({})格式错误", url));
        }
        this.url = url;
    }

    private boolean checkUrl(String url) {
        boolean ret = true;
        if (StrUtil.isBlank(url)) {
            return false;
        }
        //判断url格式
        Matcher matcher = URL_PATTERN.matcher(url);
        if(!matcher.matches()){
            return false;
        }
        return ret;
    }

}
