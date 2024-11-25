package com.zhy.repository;

import com.zhy.domain.entity.AuthRole;
import com.zhy.domain.entity.AuthUserRoles;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;

public interface AuthRoleRepository {


    AuthRole find(Id roleId);

    AuthRole find(Name roleName);


    AuthUserRoles findUserRoles(UserId userId);
}
