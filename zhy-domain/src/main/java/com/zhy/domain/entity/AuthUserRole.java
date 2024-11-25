package com.zhy.domain.entity;

import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.UserName;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/8/20 13:30
 */

@Data
public class AuthUserRole {

    private UserId userId;

    private UserName userName;

    private Id roleId;

    private Name roleName;

}
