package com.zhy.repository;

import com.zhy.domain.entity.auth.AccessToken;
import com.zhy.types.UserId;
import com.zhy.types.auth.Token;

public interface TokenRepository {

    AccessToken find(Token accessToken);

    AccessToken find(UserId userId);

    AccessToken find(Token accessToken, UserId userId);

    AccessToken save(UserId userId);
    AccessToken refresh(Token refreshToken, UserId userId);

}
