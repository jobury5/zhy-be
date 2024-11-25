package com.zhy.repository;

import com.zhy.domain.entity.AuthUrlPermRoles;
import com.zhy.domain.entity.AuthUserPerms;
import com.zhy.types.UserId;

import java.util.List;

public interface AuthPermissionRepository {

    List<AuthUrlPermRoles> findUrlPermRoles();

    AuthUserPerms findUserPerms(UserId userId);

}
