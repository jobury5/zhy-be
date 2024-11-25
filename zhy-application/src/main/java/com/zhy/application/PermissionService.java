package com.zhy.application;

import com.zhy.dto.UrlPermRolesDTO;

import java.util.List;

public interface PermissionService {

    List<UrlPermRolesDTO> getUrlPermRolesList();

    void refreshUrlPermRoles();


}
