package com.zhy.mapstruct;

import com.zhy.dataobject.AuthUserDO;
import com.zhy.domain.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthUserConverter {

    //AuthUserConverter INSTANCE = Mappers.getMapper(AuthUserConverter.class);

    @Mapping(target = "userId.userId", source = "id")
    @Mapping(target = "userName.userName", source = "userName")
    @Mapping(target = "email.address", source = "email")
    @Mapping(target = "password.password", source = "password")
    @Mapping(target = "areaNumber.areaNumber", source = "areaNumber")
    @Mapping(target = "mobile.mobile", source = "mobile")
    @Mapping(target = "tenantId.id", source = "tenantId")
    @Mapping(target = "userType.userType", source = "userType")
    AuthUser toEntity(AuthUserDO authUserDO);

    @Mapping(target = "id", source = "userId.userId")
    @Mapping(target = "userName", source = "userName.userName")
    @Mapping(target = "email", source = "email.address")
    @Mapping(target = "password", source = "password.password")
    @Mapping(target = "areaNumber", source = "areaNumber.areaNumber")
    @Mapping(target = "mobile", source = "mobile.mobile")
    @Mapping(target = "tenantId", source = "tenantId.id")
    @Mapping(target = "userType", source = "userType.userType")
    AuthUserDO toDO(AuthUser authUser);

}
