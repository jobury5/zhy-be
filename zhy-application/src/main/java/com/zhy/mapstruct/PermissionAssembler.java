package com.zhy.mapstruct;

import com.zhy.domain.entity.AuthUrlPermRoles;
import com.zhy.dto.UrlPermRolesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionAssembler {

    @Mapping(target = "urlPerm.perm", source = "urlPerm")
    AuthUrlPermRoles toEntity(UrlPermRolesDTO urlPermRolesDTO);

    @Mapping(target = "urlPerm", source = "urlPerm.perm")
    UrlPermRolesDTO toDTO(AuthUrlPermRoles authUrlPermRoles);

    List<AuthUrlPermRoles> toEntities(List<UrlPermRolesDTO> urlPermRolesDTOs);

    List<UrlPermRolesDTO> toDTOs(List<AuthUrlPermRoles> authUrlPermRoles);
}
