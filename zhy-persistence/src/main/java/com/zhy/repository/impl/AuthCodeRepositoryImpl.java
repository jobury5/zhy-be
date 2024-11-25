package com.zhy.repository.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhy.dataobject.LogAuthCodeDO;
import com.zhy.mapper.LogAuthCodeMapper;
import com.zhy.repository.AuthCodeRepository;
import com.zhy.types.AreaNumber;
import com.zhy.types.AuthCode;
import com.zhy.types.Mobile;
import com.zhy.types.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * @Author: jobury
 * @Date: 2024/9/25 15:10
 */
@Repository
public class AuthCodeRepositoryImpl implements AuthCodeRepository {

    @Autowired
    private LogAuthCodeMapper logAuthCodeMapper;

    @Override
    public boolean checkValid(AuthCode code, AreaNumber areaNumber, Mobile mobile) {
        boolean check = false;
        LambdaQueryWrapper<LogAuthCodeDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(LogAuthCodeDO::getCode, code.getCode());
        queryWrapper.eq(LogAuthCodeDO::getAreaNumber, areaNumber.getAreaNumber());
        queryWrapper.eq(LogAuthCodeDO::getMobile, mobile.getMobile());
        queryWrapper.orderByDesc(LogAuthCodeDO::getExpireAt);
        LogAuthCodeDO logAuthCodeDO = logAuthCodeMapper.selectOne(queryWrapper);
        if (null != logAuthCodeDO && logAuthCodeDO.getExpireAt().isAfter(LocalDateTimeUtil.now())) {
            check = true;
        }
        return check;
    }

    @Override
    public void save(AuthCode authCode, AreaNumber areaNumber, Mobile mobile, UserId userId) {
        LogAuthCodeDO logAuthCodeDO = new LogAuthCodeDO();
        logAuthCodeDO.setCode(authCode.getCode());
        logAuthCodeDO.setAreaNumber(areaNumber.getAreaNumber());
        logAuthCodeDO.setMobile(mobile.getMobile());
        logAuthCodeDO.setExpireAt(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), 5, ChronoUnit.MINUTES));
        logAuthCodeDO.setGmtCreate(LocalDateTimeUtil.now());
        logAuthCodeDO.setCreateBy(Optional.ofNullable(userId).map(UserId::getUserId).orElse(1L));
        logAuthCodeMapper.insert(logAuthCodeDO);
    }

}
