package com.zhy.web;

import com.zhy.aop.NotControllerResponseAdvice;
import com.zhy.application.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jobury
 * @Date: 2024/10/9 16:19
 */
@RestController
@RequestMapping("/api/cache")
@Tag(name = "缓存管理", description = "缓存刷新 API")
public class CacheController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/refresh-url-perm-roles")
    @NotControllerResponseAdvice
    @Operation(summary = "刷新url权限和角色的映射关系", description = "刷新url权限和角色的映射关系到redis缓存中")
    public String refreshUrlPermRoles() {
        permissionService.refreshUrlPermRoles();
        return "success";
    }

}
