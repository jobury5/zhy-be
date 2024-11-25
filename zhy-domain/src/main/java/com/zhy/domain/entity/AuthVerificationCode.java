package com.zhy.domain.entity;

import com.zhy.types.AuthCode;
import com.zhy.types.Mobile;
import com.zhy.types.UserId;
import lombok.Data;

@Data
public class AuthVerificationCode {

    private UserId userId;

    private Mobile mobile;

    private AuthCode authCode;

}
