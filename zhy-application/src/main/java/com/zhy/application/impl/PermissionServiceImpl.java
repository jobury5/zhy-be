package com.zhy.application.impl;

import cn.hutool.core.collection.CollUtil;
import com.zhy.application.PermissionService;
import com.zhy.constant.SysConstant;
import com.zhy.domain.entity.AuthUrlPermRoles;
import com.zhy.dto.UrlPermRolesDTO;
import com.zhy.mapstruct.PermissionAssembler;
import com.zhy.repository.AuthPermissionRepository;
import com.zhy.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jobury
 * @Date: 2024/10/8 18:12
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private AuthPermissionRepository authPermissionRepository;

    @Autowired
    private PermissionAssembler permissionAssembler;

    @Autowired
    private RedisService redisService;

    @Override
    public List<UrlPermRolesDTO> getUrlPermRolesList() {
        List<AuthUrlPermRoles> urlPermRoles = authPermissionRepository.findUrlPermRoles();
        return permissionAssembler.toDTOs(urlPermRoles);
    }

    @Override
    public void refreshUrlPermRoles() {
        List<UrlPermRolesDTO> urlPermRolesList = getUrlPermRolesList();
        if(CollUtil.isNotEmpty(urlPermRolesList)){
            Map<String, Object> permRolesMap = new HashMap<>();
            for(UrlPermRolesDTO urlPermRolesDTO: urlPermRolesList){
                permRolesMap.put(urlPermRolesDTO.getUrlPerm(), urlPermRolesDTO.getRoles());
            }
            redisService.delete(SysConstant.RedisKey.KEY_PERM_ROLES);
            redisService.hmset(SysConstant.RedisKey.KEY_PERM_ROLES, permRolesMap);
        }
    }


}
