package com.zhy.application;

import com.zhy.dto.AccessTokenDTO;
import com.zhy.dto.AuthUserDTO;
import com.zhy.types.UserId;
import com.zhy.types.auth.Token;

public interface TokenService {

    AccessTokenDTO createAccessToken(UserId userId);
    AuthUserDTO getUserByAccessToken(Token token);
    AuthUserDTO getOrRefreshUserByAccessToken(Token token);
    AccessTokenDTO refreshAccessToken(UserId userId);

}
