package com.zhy.domain.entity;

import com.zhy.types.UserId;
import com.zhy.types.UserName;
import lombok.Data;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/9 10:10
 */
@Data
public class AuthUserRoles {

    private UserId userId;

    private UserName userName;

    private List<AuthRole> authRoles;

}
