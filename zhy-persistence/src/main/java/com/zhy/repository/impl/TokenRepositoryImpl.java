package com.zhy.repository.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhy.dataobject.AuthTokenDO;
import com.zhy.domain.entity.auth.AccessToken;
import com.zhy.mapper.AuthTokenHisMapper;
import com.zhy.mapper.AuthTokenMapper;
import com.zhy.mapstruct.AuthTokenConverter;
import com.zhy.repository.TokenRepository;
import com.zhy.types.UserId;
import com.zhy.types.auth.Token;
import com.zhy.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.temporal.ChronoUnit;

/**
 * @Author: jobury
 * @Date: 2024/9/25 09:54
 */
@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private AuthTokenMapper authTokenMapper;

    @Autowired
    private AuthTokenHisMapper authTokenHisMapper;

    @Autowired
    private AuthTokenConverter authTokenConverter;

    @Override
    public AccessToken find(Token accessToken) {
        LambdaQueryWrapper<AuthTokenDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AuthTokenDO::getToken, accessToken.getToken());
        AuthTokenDO authTokenDO = authTokenMapper.selectOne(queryWrapper);
        return authTokenConverter.toEntity(authTokenDO);
    }

    @Override
    public AccessToken find(UserId userId) {
        LambdaQueryWrapper<AuthTokenDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AuthTokenDO::getUserId, userId.getUserId());
        AuthTokenDO authTokenDO = authTokenMapper.selectOne(queryWrapper);
        return authTokenConverter.toEntity(authTokenDO);
    }

    @Override
    public AccessToken find(Token accessToken, UserId userId) {
        LambdaQueryWrapper<AuthTokenDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AuthTokenDO::getToken, accessToken.getToken());
        queryWrapper.eq(AuthTokenDO::getUserId, userId.getUserId());
        AuthTokenDO authTokenDO = authTokenMapper.selectOne(queryWrapper);
        return authTokenConverter.toEntity(authTokenDO);
    }

    @Override
    public AccessToken save(UserId userId) {
        LambdaQueryWrapper<AuthTokenDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AuthTokenDO::getUserId, userId.getUserId());
        AuthTokenDO authTokenDO = authTokenMapper.selectOne(queryWrapper);
        //如果已经存在access token，则移入历史表里
        if (authTokenDO != null) {
            authTokenHisMapper.insert(authTokenConverter.toHisDO(authTokenDO));
            authTokenMapper.deleteById(authTokenDO.getId());
        }
        AuthTokenDO newToken = new AuthTokenDO();
        newToken.setUserId(userId.getUserId());
        //设置带时间戳的access token
        newToken.setToken(RandomGenerator.genAccessToken());
        //令牌过期时间设置为2小时
        newToken.setExpireAt(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), 2, ChronoUnit.HOURS));
        //newToken.setExpireAt(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), 2, ChronoUnit.SECONDS));
        //设置带时间戳的refresh token
        newToken.setRefreshToken(RandomGenerator.genAccessToken());
        //刷新令牌过期时间设置为30天
        newToken.setRefreshExpireAt(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), 30, ChronoUnit.DAYS));
        newToken.setCreateBy(userId.getUserId());
        authTokenMapper.insert(newToken);
        return authTokenConverter.toEntity(newToken);
    }

    @Override
    public AccessToken refresh(Token refreshToken, UserId userId) {
        AccessToken newToken = null;
        LambdaQueryWrapper<AuthTokenDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AuthTokenDO::getUserId, userId.getUserId());
        queryWrapper.eq(AuthTokenDO::getRefreshToken, refreshToken.getToken());
        AuthTokenDO authTokenDO = authTokenMapper.selectOne(queryWrapper);
        //先判断刷新令牌是否存在并且在有效期内
        if (authTokenDO != null && authTokenDO.getRefreshExpireAt().isAfter(LocalDateTimeUtil.now())) {
            newToken = this.save(userId);
        }
        return newToken;
    }

}
