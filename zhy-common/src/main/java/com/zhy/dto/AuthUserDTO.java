package com.zhy.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private List<String> userRoles;

    private AccessTokenDTO accessToken;

    private Boolean refresh = false;

}
