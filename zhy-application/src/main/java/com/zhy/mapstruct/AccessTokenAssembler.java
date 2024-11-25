package com.zhy.mapstruct;

import com.zhy.domain.entity.auth.AccessToken;
import com.zhy.dto.AccessTokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring")
public interface AccessTokenAssembler {

    AccessTokenAssembler INSTANCE = Mappers.getMapper(AccessTokenAssembler.class);

    @Mapping(target = "token.token", source = "accessToken")
    @Mapping(target = "expireAt.expireTime", source = "expireTime")
    @Mapping(target = "refreshToken.token", source = "refreshToken")
    @Mapping(target = "refreshExpireAt.expireTime", source = "refreshExpireTime")
    @Mapping(target = "userId.userId", source = "userId")
    AccessToken toEntity(AccessTokenDTO accessTokenDTO);

    @Mapping(target = "accessToken", source = "token.token")
    @Mapping(target = "expireTime", source = "expireAt.expireTime")
    @Mapping(target = "refreshToken", source = "refreshToken.token")
    @Mapping(target = "refreshExpireTime", source = "refreshExpireAt.expireTime")
    @Mapping(target = "userId", source = "userId.userId")
    AccessTokenDTO toDTO(AccessToken accessToken);

    default Long localDateTimeToLong(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return zonedDateTime.toInstant().toEpochMilli();
    }

    default LocalDateTime longToLocalDateTime(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Asia/Shanghai"));
        return zonedDateTime.toLocalDateTime();
    }

}
