package com.zhy.dataobject;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/8 17:03
 */
@Getter
@Setter
public class AuthUrlPermRolesDO {


    private String name;

    private String urlPerm;

    private List<String> roles;

}
