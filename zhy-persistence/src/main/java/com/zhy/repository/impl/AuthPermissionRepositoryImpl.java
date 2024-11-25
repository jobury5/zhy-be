package com.zhy.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.zhy.dataobject.AuthUrlPermRolesDO;
import com.zhy.dataobject.AuthUserPermDO;
import com.zhy.domain.entity.AuthUrlPermRoles;
import com.zhy.domain.entity.AuthUserPerms;
import com.zhy.mapper.AuthPermissionMapper;
import com.zhy.mapstruct.AuthPermissionConverter;
import com.zhy.repository.AuthPermissionRepository;
import com.zhy.types.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/8 17:54
 */
@Repository
public class AuthPermissionRepositoryImpl implements AuthPermissionRepository {

    @Autowired
    private AuthPermissionMapper authPermissionMapper;

    @Autowired
    private AuthPermissionConverter authPermissionConverter;

    @Override
    public List<AuthUrlPermRoles> findUrlPermRoles() {
        List<AuthUrlPermRolesDO> urlPermRoles = authPermissionMapper.getUrlPermRoles();
        return authPermissionConverter.toAuthUrlPermRolesList(urlPermRoles);
    }

    @Override
    public AuthUserPerms findUserPerms(UserId userId) {
        List<AuthUserPermDO> userPermissions = authPermissionMapper.getUserPermission(userId.getUserId());
        AuthUserPerms authUserPerms = new AuthUserPerms();
        authUserPerms.setUserId(userId);
        List<String> urlPerms = new ArrayList<>();
        List<String> btnPerms = new ArrayList<>();
        for (AuthUserPermDO authUserPermDO : userPermissions) {
            if(StrUtil.isNotBlank(authUserPermDO.getUrlPerm())){
                urlPerms.add(authUserPermDO.getUrlPerm());
            }
            if(StrUtil.isNotBlank(authUserPermDO.getBtnPerm())){
                btnPerms.add(authUserPermDO.getBtnPerm());
            }
        }
        authUserPerms.setUrlPerms(urlPerms);
        authUserPerms.setBtnPerms(btnPerms);
        return authUserPerms;
    }


}
