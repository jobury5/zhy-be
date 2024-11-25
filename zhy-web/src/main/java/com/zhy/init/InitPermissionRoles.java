package com.zhy.init;

import com.zhy.application.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: jobury
 * @Date: 2024/10/8 18:18
 */
@Component
public class InitPermissionRoles implements CommandLineRunner {

    @Autowired
    private PermissionService permissionService;

    @Override
    public void run(String... args) throws Exception {
        permissionService.refreshUrlPermRoles();
    }


}
