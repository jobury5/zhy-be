package com.zhy.util;

import com.zhy.domain.entity.AuthRole;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

    public static List<String> toRoleList(List<AuthRole> authRoleList){
        List<String> roles = new ArrayList<>();
        for(AuthRole authRole : authRoleList){
            roles.add(authRole.getRoleName().getName());
        }
        return roles;
    }


}