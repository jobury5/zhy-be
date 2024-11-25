package com.zhy.mapstruct;

import com.zhy.dataobject.AuthTokenDO;
import com.zhy.dataobject.AuthTokenHisDO;
import com.zhy.domain.entity.auth.AccessToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthTokenConverter {

    @Mapping(target = "userId.userId", source = "userId")
    @Mapping(target = "token.token", source = "token")
    @Mapping(target = "expireAt.expireTime", source = "expireAt")
    @Mapping(target = "refreshToken.token", source = "refreshToken")
    @Mapping(target = "refreshExpireAt.expireTime", source = "refreshExpireAt")
    AccessToken toEntity(AuthTokenDO authTokenDO);

    @Mapping(target = "userId", source = "userId.userId")
    @Mapping(target = "token", source = "token.token")
    @Mapping(target = "expireAt", source = "expireAt.expireTime")
    @Mapping(target = "refreshToken", source = "refreshToken.token")
    @Mapping(target = "refreshExpireAt", source = "refreshExpireAt.expireTime")
    AuthTokenDO toDO(AccessToken accessToken);

    AuthTokenHisDO toHisDO(AuthTokenDO authTokenDO);


}
