package com.zhy.external;

import com.zhy.types.AreaNumber;
import com.zhy.types.AuthCode;
import com.zhy.types.Mobile;

public interface SmsService {

    //发送验证码
    Boolean sendAuthCode(AreaNumber areaNumber, Mobile mobile, AuthCode code);


}
