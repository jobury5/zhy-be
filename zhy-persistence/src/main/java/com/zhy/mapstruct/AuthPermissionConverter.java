package com.zhy.mapstruct;

import com.zhy.dataobject.AuthUrlPermRolesDO;
import com.zhy.domain.entity.AuthUrlPermRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthPermissionConverter {

    @Mapping(target = "urlPerm.perm", source = "urlPerm")
    @Mapping(target = "name.name", source = "name")
    AuthUrlPermRoles toAuthUrlPermRoles(AuthUrlPermRolesDO authUrlPermRolesDO);

    @Mapping(target = "urlPerm", source = "urlPerm.perm")
    @Mapping(target = "name", source = "name.name")
    AuthUrlPermRolesDO toAuthUrlPermRolesDO(AuthUrlPermRoles authUrlPermRoles);

    List<AuthUrlPermRoles> toAuthUrlPermRolesList(List<AuthUrlPermRolesDO> authUrlPermRolesDOs);

    List<AuthUrlPermRolesDO> toAuthUrlPermRolesDOList(List<AuthUrlPermRoles> authUrlPermRolesList);

}
