package com.zhy.web;

import com.zhy.application.AttachService;
import com.zhy.cqe.attach.AttachUploadCommand;
import com.zhy.cqe.attach.AttachUrlQuery;
import com.zhy.dto.AttachDownloadDTO;
import com.zhy.dto.AttachUploadDTO;
import com.zhy.types.Id;
import com.zhy.types.Url;
import com.zhy.types.UserId;
import com.zhy.types.file.FileName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author: jobury
 * @Date: 2024/9/21 21:12
 */
@RestController
@RequestMapping("/api/attach")
@Tag(name = "文件操作", description = "文件操作相关 API")
public class AttachController {

    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件到OSS中")
    public AttachUploadDTO upload(@ModelAttribute @Valid AttachUploadCommand command) throws IOException {
        FileName originalFilename = new FileName(command.getFile().getOriginalFilename());
        Id newAttachId = attachService.uploadAttach(new Id(command.getTypeId()), command.getFile().getInputStream(), originalFilename, new UserId(command.getUserId()));
        AttachUploadDTO attachUploadDTO = new AttachUploadDTO();
        attachUploadDTO.setAttachId(newAttachId.getId());
        return attachUploadDTO;
    }

    @PostMapping("/download-url")
    @Operation(summary = "下载文件", description = "从OSS下载文件，生成下载地址url")
    public AttachDownloadDTO getDownLoadUrl(@RequestBody @Valid AttachUrlQuery query){
        Url url = attachService.getUrlByAttachId(new Id(query.getAttachId()));
        AttachDownloadDTO dto = new AttachDownloadDTO();
        dto.setAttachId(query.getAttachId());
        dto.setUrl(url.getUrl());
        return dto;
    }


}
