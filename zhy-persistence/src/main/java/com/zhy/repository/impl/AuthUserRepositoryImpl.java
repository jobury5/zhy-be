package com.zhy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhy.dataobject.AuthUserDO;
import com.zhy.domain.entity.AuthUser;
import com.zhy.mapper.AuthUserMapper;
import com.zhy.mapstruct.AuthUserConverter;
import com.zhy.repository.AuthUserRepository;
import com.zhy.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobury
 * @since 2024-02-06
 */
@Repository
public class AuthUserRepositoryImpl implements AuthUserRepository {

    @Autowired
    private AuthUserConverter authUserConverter;

    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public AuthUser find(UserId userId) {
        AuthUserDO authUserDO = authUserMapper.selectById(userId.getUserId());
        return authUserConverter.toEntity(authUserDO);
    }

    @Override
    public AuthUser find(UserName userName) {
        LambdaQueryWrapper<AuthUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserDO::getUserName, userName.getUserName());
        AuthUserDO authUserDO = authUserMapper.selectOne(queryWrapper);
        return authUserConverter.toEntity(authUserDO);
    }

    @Override
    public AuthUser findGeneral(UserName userName) {
        LambdaQueryWrapper<AuthUserDO> queryWrapper = new LambdaQueryWrapper<>();
        // 连接多个条件
        queryWrapper.and(w -> w
                .eq(AuthUserDO::getUserName, userName.getUserName())
                .or()
                .eq(AuthUserDO::getMobile, userName.getUserName())
                .or()
                .eq(AuthUserDO::getEmail, userName.getUserName()));
        AuthUserDO authUserDO = authUserMapper.selectOne(queryWrapper);
        return authUserConverter.toEntity(authUserDO);
    }

    @Override
    public AuthUser find(MailAddress email) {
        LambdaQueryWrapper<AuthUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserDO::getEmail, email.getAddress());
        AuthUserDO authUserDO = authUserMapper.selectOne(queryWrapper);
        return authUserConverter.toEntity(authUserDO);
    }

    @Override
    public AuthUser find(AreaNumber areaNumber, Mobile mobile) {
        LambdaQueryWrapper<AuthUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUserDO::getAreaNumber, areaNumber.getAreaNumber());
        queryWrapper.eq(AuthUserDO::getMobile, mobile.getMobile());
        AuthUserDO authUserDO = authUserMapper.selectOne(queryWrapper);
        return authUserConverter.toEntity(authUserDO);
    }

}
