package com.zhy.web;

import com.zhy.aop.NotControllerResponseAdvice;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jobury
 * @Date: 2024/9/10 15:56
 */

@RestController
@RequestMapping("/api/health")
@Tag(name = "健康检查", description = "心跳检查 API")
public class HealthController {

    @GetMapping("/heart")
    @NotControllerResponseAdvice
    public String heart() {
        return "success";
    }

    @GetMapping("/test")
    public String test() {
        return "hahahaha, just test";
    }

}
