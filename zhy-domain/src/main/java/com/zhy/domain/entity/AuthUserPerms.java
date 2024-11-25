package com.zhy.domain.entity;

import com.zhy.types.UserId;
import com.zhy.types.UserName;
import lombok.Data;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/9 14:57
 */
@Data
public class AuthUserPerms {

    private UserId userId;

    private UserName userName;

    private List<String> urlPerms;

    private List<String> btnPerms;

}
