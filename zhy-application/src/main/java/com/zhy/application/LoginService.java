package com.zhy.application;

import com.zhy.dto.AccessTokenDTO;
import com.zhy.types.*;

public interface LoginService {

    AccessTokenDTO login(UserName userName, Password password);

    Boolean sendAuthCode(AreaNumber areaNumber, Mobile mobile);

    AccessTokenDTO loginByAuthCode(AreaNumber areaNumber, Mobile mobile, AuthCode authCode);
}
