package com.zhy.repository;

import com.zhy.domain.entity.AuthUserRole;
import com.zhy.types.Id;
import com.zhy.types.UserId;

import java.util.List;

public interface AuthUserRoleRepository {

    List<AuthUserRole> find(Id roleId);

    List<AuthUserRole> find(UserId userId);

    List<AuthUserRole> findByCon(UserId userId, Id roleId);

}
