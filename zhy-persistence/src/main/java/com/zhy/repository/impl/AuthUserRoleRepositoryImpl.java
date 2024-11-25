package com.zhy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhy.dataobject.AuthUserRoleDO;
import com.zhy.domain.entity.AuthUserRole;
import com.zhy.mapper.AuthUserRoleMapper;
import com.zhy.mapstruct.AuthUserRoleConverter;
import com.zhy.repository.AuthRoleRepository;
import com.zhy.repository.AuthUserRepository;
import com.zhy.repository.AuthUserRoleRepository;
import com.zhy.types.Id;
import com.zhy.types.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/8/20 13:40
 */

@Repository
public class AuthUserRoleRepositoryImpl implements AuthUserRoleRepository {

    @Autowired
    private AuthUserRoleConverter authUserRoleConverter;

    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Override
    public List<AuthUserRole> find(Id roleId) {
        LambdaQueryWrapper<AuthUserRoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserRoleDO::getRoleId, roleId.getId());
        List<AuthUserRoleDO> authUserRoles = authUserRoleMapper.selectList(queryWrapper);
        List<AuthUserRole> entityList = authUserRoleConverter.toEntityList(authUserRoles);
        for(AuthUserRole authUserRole : entityList){
            authUserRole.setUserName(authUserRepository.find(authUserRole.getUserId()).getUserName());
            authUserRole.setRoleName(authRoleRepository.find(authUserRole.getRoleId()).getRoleName());
        }
        return entityList;
    }

    @Override
    public List<AuthUserRole> find(UserId userId) {
        LambdaQueryWrapper<AuthUserRoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserRoleDO::getUserId, userId.getUserId());
        List<AuthUserRoleDO> authUserRoles = authUserRoleMapper.selectList(queryWrapper);
        return authUserRoleConverter.toEntityList(authUserRoles);
    }

    @Override
    public List<AuthUserRole> findByCon(UserId userId, Id roleId) {
        List<AuthUserRoleDO> authUserRoles = authUserRoleMapper.selectByCon(userId.getUserId(), roleId.getId());
        return authUserRoleConverter.toEntityList(authUserRoles);
    }


}
