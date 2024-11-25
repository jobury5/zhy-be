package com.zhy.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/25 11:03
 */

@Data
public class AccessTokenDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String accessToken;

    private Long expireTime;

    private String refreshToken;

    private Long refreshExpireTime;

    private Long userId;

    private List<String> userRoles;

    private List<String> userPerms;

}
