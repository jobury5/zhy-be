package com.zhy.repository;

import com.zhy.types.AreaNumber;
import com.zhy.types.AuthCode;
import com.zhy.types.Mobile;
import com.zhy.types.UserId;

public interface AuthCodeRepository {

    boolean checkValid(AuthCode code, AreaNumber areaNumber, Mobile mobile);

    void save(AuthCode authCode, AreaNumber areaNumber, Mobile mobile, UserId userId);

}
