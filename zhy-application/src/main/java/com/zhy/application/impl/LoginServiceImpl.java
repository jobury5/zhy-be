package com.zhy.application.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zhy.application.LoginService;
import com.zhy.domain.entity.AuthUser;
import com.zhy.domain.entity.AuthUserPerms;
import com.zhy.domain.entity.AuthUserRoles;
import com.zhy.domain.entity.auth.AccessToken;
import com.zhy.dto.AccessTokenDTO;
import com.zhy.exception.ApiException;
import com.zhy.external.SmsService;
import com.zhy.mapstruct.AccessTokenAssembler;
import com.zhy.repository.*;
import com.zhy.result.AppCode;
import com.zhy.types.*;
import com.zhy.util.ConvertUtil;
import com.zhy.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthCodeRepository authCodeRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private AuthPermissionRepository authPermissionRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private ThreadPoolTaskExecutor customExecutor;

    @Autowired
    private AccessTokenAssembler accessTokenAssembler;

    @Override
    public AccessTokenDTO login(UserName userName, Password password) {
        // 使用userName登录
        AuthUser authUser = authUserRepository.findGeneral(userName);
        if (null == authUser) {
            throw new ApiException(AppCode.USER_NAME_NOT_EXIST, StrUtil.format("user name{} not exist", userName));
        }
        if (!authUser.getPassword().getPassword().equalsIgnoreCase(SecureUtil.md5(password.getPassword()))) {
            throw new ApiException(AppCode.PASSWORD_WRONG, "password is not correct");
        }
        //登录成功，生成access token
        AccessToken accessToken = tokenRepository.save(authUser.getUserId());
        AccessTokenDTO accessTokenDTO = accessTokenAssembler.toDTO(accessToken);
        //设置用户角色
        AuthUserRoles userRoles = authRoleRepository.findUserRoles(accessToken.getUserId());
        if (userRoles != null) {
            List<String> roleList = ConvertUtil.toRoleList(userRoles.getAuthRoles());
            accessTokenDTO.setUserRoles(roleList);
        }
        //设置按钮权限
        AuthUserPerms userPerms = authPermissionRepository.findUserPerms(authUser.getUserId());
        if (userPerms != null && CollUtil.isNotEmpty(userPerms.getBtnPerms())) {
            accessTokenDTO.setUserPerms(userPerms.getBtnPerms());
        }
        return accessTokenDTO;
    }

    @Override
    public Boolean sendAuthCode(AreaNumber areaNumber, Mobile mobile) {
        //4位验证码
        AuthCode authCode = new AuthCode(RandomGenerator.genRandomVerCode(4));
        //调用自定义线程池异步发送验证码
        CompletableFuture.runAsync(() -> smsService.sendAuthCode(areaNumber, mobile, authCode), customExecutor);
        //保存验证码
        authCodeRepository.save(authCode, areaNumber, mobile, null);
        return true;
    }

    @Override
    public AccessTokenDTO loginByAuthCode(AreaNumber areaNumber, Mobile mobile, AuthCode authCode) {
        //判断该手机号是否存在
        AuthUser authUser = authUserRepository.find(areaNumber, mobile);
        if (null == authUser) {
            throw new ApiException(AppCode.MOBILE_NOT_EXIST, StrUtil.format("mobile{} not exist", mobile.getMobile()));
        }
        //该验证码是否存在且有效
        if (!authCodeRepository.checkValid(authCode, areaNumber, mobile)) {
            throw new ApiException(AppCode.AUTH_CODE_WRONG_OR_EXPIRED, StrUtil.format("authCode{} is wrong or is expired", authCode.getCode()));
        }
        //登录成功，生成access token
        AccessToken accessToken = tokenRepository.save(authUser.getUserId());
        AccessTokenDTO accessTokenDTO = accessTokenAssembler.toDTO(accessToken);
        //设置用户角色
        AuthUserRoles userRoles = authRoleRepository.findUserRoles(accessToken.getUserId());
        if (userRoles != null) {
            List<String> roleList = ConvertUtil.toRoleList(userRoles.getAuthRoles());
            accessTokenDTO.setUserRoles(roleList);
        }
        //设置按钮权限
        AuthUserPerms userPerms = authPermissionRepository.findUserPerms(authUser.getUserId());
        if (userPerms != null && CollUtil.isNotEmpty(userPerms.getBtnPerms())) {
            accessTokenDTO.setUserPerms(userPerms.getBtnPerms());
        }
        return accessTokenDTO;
    }

}