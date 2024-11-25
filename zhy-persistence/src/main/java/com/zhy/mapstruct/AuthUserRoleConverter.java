package com.zhy.mapstruct;

import com.zhy.dataobject.AuthUserRoleDO;
import com.zhy.domain.entity.AuthUserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthUserRoleConverter {

    @Mapping(target = "userId.userId", source = "userId")
    @Mapping(target = "roleId.id", source = "roleId")
    AuthUserRole toEntity(AuthUserRoleDO authUserRoleDO);

    @Mapping(target = "userId", source = "userId.userId")
    @Mapping(target = "roleId", source = "roleId.id")
    AuthUserRoleDO toDO(AuthUserRole authUserRole);

    List<AuthUserRole> toEntityList(List<AuthUserRoleDO> authUserRoleDOs);

    List<AuthUserRoleDO> toDOList(List<AuthUserRole> authUserRoles);
}
