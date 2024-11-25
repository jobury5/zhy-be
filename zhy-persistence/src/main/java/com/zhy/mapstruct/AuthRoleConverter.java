package com.zhy.mapstruct;

import com.zhy.dataobject.AuthRoleDO;
import com.zhy.domain.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthRoleConverter {

    @Mapping(target = "roleId.id", source = "id")
    @Mapping(target = "roleName.name", source = "name")
    AuthRole toEntity(AuthRoleDO authRoleDO);

    @Mapping(target = "id", source = "roleId.id")
    @Mapping(target = "name", source = "roleName.name")
    AuthRoleDO toDO(AuthRole authRole);

}
