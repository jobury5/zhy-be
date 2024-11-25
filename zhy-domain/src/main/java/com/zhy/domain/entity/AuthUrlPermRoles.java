package com.zhy.domain.entity;

import com.zhy.types.Name;
import com.zhy.types.auth.UrlPerm;
import lombok.Data;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/8 17:22
 */

@Data
public class AuthUrlPermRoles {

    private Name name;

    private UrlPerm urlPerm;

    private List<String> roles;

}
