package com.zhy.types;

import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.result.SysCode;
import lombok.Value;

/**
 * @Author: jobury
 * @Date: 2024/9/11 18:48
 */

@Value
public class MailContent {

    String subject;

    String content;

    public MailContent(String subject, String content) {
        if (!checkContent(subject, content)) {
            throw new ApiException(SysCode.DATA_VALIDATE_ERROR, StrUtil.format("mailSubject({})或者mailContent({})格式错误", subject, content));
        }
        this.subject = subject;
        this.content = content;
    }

    private boolean checkContent(String subject, String content) {
        boolean ret = true;
        if (StrUtil.isBlank(subject) || StrUtil.isBlank(content)) {
            ret = false;
        }
        return ret;
    }


}
