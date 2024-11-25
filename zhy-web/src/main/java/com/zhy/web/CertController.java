package com.zhy.web;

import com.zhy.application.CrewCertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jobury
 * @Date: 2024/9/11 10:28
 */
@RestController
@RequestMapping("/api/cert")
@Tag(name = "船员证书", description = "证书 API")
public class CertController {

    @Autowired
    private CrewCertService crewCertService;

    @PostMapping("/get-url")
    @Operation(summary = "获取下载地址", description = "返回一个下载地址")
    public String getUrl(@Parameter(description = "文件名") @RequestBody @Valid String fileName){
        String certFileUrl = crewCertService.getCertFileUrl(fileName);
        return certFileUrl;
    }


}
