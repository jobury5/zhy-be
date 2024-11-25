package com.zhy.domain.entity.auth;

import com.zhy.types.ExpireTime;
import com.zhy.types.UserId;
import com.zhy.types.auth.Token;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/24 19:00
 */

@Data
public class AccessToken {

    private Token token;

    private ExpireTime expireAt;

    private Token refreshToken;

    private ExpireTime refreshExpireAt;

    private UserId userId;


}
