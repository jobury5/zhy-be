package com.zhy.web;

import com.zhy.application.LoginService;
import com.zhy.application.TokenService;
import com.zhy.constant.BizConstant;
import com.zhy.cqe.SendAuthCodeEvent;
import com.zhy.cqe.UserLoginByAuthCodeCommand;
import com.zhy.cqe.UserLoginCommand;
import com.zhy.cqe.token.CreateAccessTokenCommand;
import com.zhy.cqe.token.RefreshAccessTokenCommand;
import com.zhy.cqe.token.UserByAccessTokenQuery;
import com.zhy.dto.AccessTokenDTO;
import com.zhy.dto.AuthUserDTO;
import com.zhy.types.*;
import com.zhy.types.auth.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jobury
 * @Date: 2024/9/24 18:55
 */
@Tag(name = "登录令牌", description = "登录令牌 API")
@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/create-access-token")
    @Operation(summary = "生成新的access token", description = "根据用户ID生成新的access token")
    public AccessTokenDTO createAccessToken(@RequestBody @Valid CreateAccessTokenCommand commmand){
        return tokenService.createAccessToken(new UserId(commmand.getUserId()));
    }

    @PostMapping("/get-user-by-access-token")
    @Operation(summary = "获取登录用户信息", description = "通过access token获取登录用户信息")
    public AuthUserDTO getUserByAccessToken(@RequestBody @Valid UserByAccessTokenQuery query){
        return tokenService.getUserByAccessToken(new Token(query.getToken()));
    }

    @PostMapping("/get-or-refresh-user-by-access-token")
    @Operation(summary = "获取登录用户信息", description = "通过access token获取登录用户信息，如果access token过期，则刷新token并返回")
    public AuthUserDTO getOrRefreshUserByAccessToken(@RequestBody @Valid UserByAccessTokenQuery query){
        return tokenService.getOrRefreshUserByAccessToken(new Token(query.getToken()));
    }

    @PostMapping("/refresh-access-token")
    @Operation(summary = "刷新access token", description = "通过用户id强制刷新access token")
    public AccessTokenDTO refreshAccessToken(@RequestBody @Valid RefreshAccessTokenCommand command){
        return tokenService.refreshAccessToken(new UserId(command.getUserId()));
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户用户名和密码登录，返回access token和用户信息")
    public AccessTokenDTO login(@RequestBody @Valid UserLoginCommand query) {
        return loginService.login(new UserName(query.getUserName()), new Password(query.getPassword()));
    }

    @PostMapping("/login-by-auth-code")
    @Operation(summary = "验证码登录", description = "手机验证码登录")
    public AccessTokenDTO loginByAuthCode(@RequestBody @Valid UserLoginByAuthCodeCommand query) {
        AreaNumber areaNumber = query.getAreaNumber() != null ? new AreaNumber(query.getAreaNumber()) : new AreaNumber(BizConstant.CHINA_AREA_NUMBER);
        return loginService.loginByAuthCode(areaNumber, new Mobile(query.getMobile()), new AuthCode(query.getAuthCode()));
    }

    @PostMapping("/send-auth-code")
    @Operation(summary = "发送验证码", description = "发送手机验证码")
    public Boolean sendAuthCode(@RequestBody @Valid SendAuthCodeEvent event) {
        AreaNumber areaNumber = event.getAreaNumber() != null ? new AreaNumber(event.getAreaNumber()) : new AreaNumber(BizConstant.CHINA_AREA_NUMBER);
        return loginService.sendAuthCode(areaNumber, new Mobile(event.getMobile()));
    }

}
