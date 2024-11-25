package com.zhy.application.impl;

import cn.hutool.core.util.StrUtil;
import com.zhy.application.TokenService;
import com.zhy.domain.entity.AuthUserRoles;
import com.zhy.domain.entity.auth.AccessToken;
import com.zhy.dto.AccessTokenDTO;
import com.zhy.dto.AuthUserDTO;
import com.zhy.exception.ApiException;
import com.zhy.mapstruct.AccessTokenAssembler;
import com.zhy.repository.AuthRoleRepository;
import com.zhy.repository.TokenRepository;
import com.zhy.result.SysCode;
import com.zhy.types.UserId;
import com.zhy.types.auth.Token;
import com.zhy.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/24 18:56
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private AccessTokenAssembler accessTokenAssembler;

    @Override
    public AccessTokenDTO createAccessToken(UserId userId) {
        AccessToken accessToken = tokenRepository.save(userId);
        return accessTokenAssembler.toDTO(accessToken);
    }

    @Override
    public AuthUserDTO getUserByAccessToken(Token token) {
        AccessToken accessToken = tokenRepository.find(token);
        if (accessToken == null) {
            throw new ApiException(SysCode.ACCESS_TOKEN_NOT_EXISTS, StrUtil.format("accessToken({})不存在", token.getToken()));
        }
        if (accessToken.getExpireAt().isExpired()) {
            throw new ApiException(SysCode.ACCESS_TOKEN_EXPIRED, StrUtil.format("accessToken({})已过期", token.getToken()));
        }
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserId(accessToken.getUserId().getUserId());
        authUserDTO.setAccessToken(accessTokenAssembler.toDTO(accessToken));
        return authUserDTO;
    }

    @Override
    public AuthUserDTO getOrRefreshUserByAccessToken(Token token) {
        AccessToken accessToken = tokenRepository.find(token);
        AuthUserDTO authUserDTO = new AuthUserDTO();
        if (accessToken == null) {
            throw new ApiException(SysCode.ACCESS_TOKEN_NOT_EXISTS, StrUtil.format("accessToken({})不存在", token.getToken()));
        }
        authUserDTO.setUserId(accessToken.getUserId().getUserId());
        AuthUserRoles userRoles = authRoleRepository.findUserRoles(accessToken.getUserId());
        if(userRoles != null) {
            List<String> roleList = ConvertUtil.toRoleList(userRoles.getAuthRoles());
            authUserDTO.setUserRoles(roleList);
        }
        if (accessToken.getExpireAt().isExpired() ) {
            if(!accessToken.getRefreshExpireAt().isExpired()) {
                AccessToken newToken = tokenRepository.refresh(accessToken.getRefreshToken(), accessToken.getUserId());
                authUserDTO.setAccessToken(accessTokenAssembler.toDTO(newToken));
                authUserDTO.setRefresh(true);
            }
            else {
                throw new ApiException(SysCode.REFRESH_TOKEN_EXPIRED, StrUtil.format("refreshToken({})已过期", accessToken.getRefreshToken()));
            }
        }
        else{
            authUserDTO.setAccessToken(accessTokenAssembler.toDTO(accessToken));
        }
        return authUserDTO;
    }

    @Override
    public AccessTokenDTO refreshAccessToken(UserId userId) {
        AccessToken accessToken = tokenRepository.find(userId);
        if (accessToken == null) {
            throw new ApiException(SysCode.ACCESS_TOKEN_NOT_EXISTS, StrUtil.format("userId({})的accessToken不存在", userId.getUserId()));
        }
        AccessToken refresh = tokenRepository.refresh(accessToken.getRefreshToken(), userId);
        return accessTokenAssembler.toDTO(refresh);
    }

}
