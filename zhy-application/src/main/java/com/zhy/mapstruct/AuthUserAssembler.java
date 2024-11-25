package com.zhy.mapstruct;

import com.zhy.domain.entity.AuthUser;
import com.zhy.dto.AuthUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthUserAssembler {

    AuthUserAssembler INSTANCE = Mappers.getMapper(AuthUserAssembler.class);

    @Mapping(target = "userName.userName", source = "userName")
    @Mapping(target = "userId.userId", source = "userId")
    AuthUser toEntity(AuthUserDTO authUserDTO);

    @Mapping(target = "userName", source = "userName.userName")
    @Mapping(target = "userId", source = "userId.userId")
    AuthUserDTO toDTO(AuthUser authUser);


}
