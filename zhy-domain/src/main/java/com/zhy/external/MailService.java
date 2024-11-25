package com.zhy.external;

import com.zhy.types.MailAddress;
import com.zhy.types.MailContent;

import java.util.Map;

public interface MailService {

    //邮件发送
    Boolean sendMail(MailAddress toMail, MailContent mailContent, Map<String, Object> paramMap);


}
