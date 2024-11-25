package com.zhy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhy.dataobject.AuthRoleDO;
import com.zhy.dataobject.AuthUserRoleDO;
import com.zhy.domain.entity.AuthRole;
import com.zhy.domain.entity.AuthUserRoles;
import com.zhy.mapper.AuthRoleMapper;
import com.zhy.mapper.AuthUserRoleMapper;
import com.zhy.mapstruct.AuthRoleConverter;
import com.zhy.repository.AuthRoleRepository;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/8/20 13:31
 */

@Repository
public class AuthRoleRepositoryImpl implements AuthRoleRepository {

    @Autowired
    private AuthRoleMapper authRoleMapper;

    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;

    @Autowired
    private AuthRoleConverter authRoleConverter;

    @Override
    public AuthRole find(Id roleId) {
        AuthRoleDO authRoleDO = authRoleMapper.selectById(roleId.getId());
        return authRoleConverter.toEntity(authRoleDO);
    }

    @Override
    public AuthRole find(Name roleName) {
        LambdaQueryWrapper<AuthRoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthRoleDO::getName, roleName.getName());
        AuthRoleDO authRoleDO = authRoleMapper.selectOne(queryWrapper);
        return authRoleConverter.toEntity(authRoleDO);
    }

    @Override
    public AuthUserRoles findUserRoles(UserId userId) {
        LambdaQueryWrapper<AuthUserRoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserRoleDO::getUserId, userId.getUserId());
        List<AuthUserRoleDO> authUserRoleDOS = authUserRoleMapper.selectList(queryWrapper);
        AuthUserRoles authUserRoles = new AuthUserRoles();
        authUserRoles.setUserId(userId);
        List<AuthRole> authRoles = new ArrayList<>();
        for(AuthUserRoleDO authUserRoleDO: authUserRoleDOS) {
            AuthRole authRole = this.find(new Id(authUserRoleDO.getRoleId()));
            authRoles.add(authRole);
        }
        authUserRoles.setAuthRoles(authRoles);
        return authUserRoles;
    }

}
