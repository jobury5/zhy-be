package com.zhy.web;

import com.zhy.application.DictService;
import com.zhy.application.SysCertService;
import com.zhy.application.SysTemplateService;
import com.zhy.cqe.sys.*;
import com.zhy.dto.DictDataDTO;
import com.zhy.dto.SysCertDTO;
import com.zhy.dto.SysTemplateDTO;
import com.zhy.dto.ZhyPageDTO;
import com.zhy.types.Id;
import com.zhy.types.UserId;
import com.zhy.types.sys.DictType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/19 13:52
 */

@RestController
@RequestMapping("/api/sys")
@Tag(name = "系统管理", description = "系统管理 API")
public class SysController {

    @Autowired
    private DictService dictService;

    @Autowired
    private SysCertService sysCertService;

    @Autowired
    private SysTemplateService sysTemplateService;

    @PostMapping("/dict-data-list")
    @Operation(summary = "获取字典数据", description = "获取字典数据列表")
    public List<DictDataDTO> getSysDictDataList(@RequestBody @Valid DictDataListQuery query){
        return dictService.getDictDatasByType(new DictType(query.getDictType()));
    }

    @PostMapping("/cert-list")
    @Operation(summary = "查询证书列表", description = "根据条件查询证书列表")
    public ZhyPageDTO<SysCertDTO> getSysCertList(@RequestBody @Valid SysCertListQuery query){
        return sysCertService.getSysCertsByCondition(query);
    }

    @PostMapping("/cert-detail")
    @Operation(summary = "获取证书详情", description = "根据证书id获取证书详情")
    public SysCertDTO getSysCertDetail(@RequestBody @Valid SysCertDetailQuery query){
        return sysCertService.getSysCertDetailById(new Id(query.getCertId()));
    }

    @PostMapping("/add-cert")
    @Operation(summary = "新增证书", description = "新增证书")
    public SysCertDTO addSysCert(@RequestBody @Valid SysCertAddCommand command){
        return sysCertService.addSysCert(command);
    }

    @PostMapping("/update-cert")
    @Operation(summary = "修改证书", description = "修改证书")
    public SysCertDTO updateSysCert(@RequestBody @Valid SysCertUpdateCommand command){
        return sysCertService.updateSysCert(command);
    }

    @PostMapping("/delete-cert")
    @Operation(summary = "删除证书", description = "删除证书")
    public boolean deleteSysCert(@RequestBody @Valid SysCertDeleteCommand command){
        return sysCertService.deleteSysCert(new Id(command.getCertId()), new UserId(command.getUserId()));
    }


    @PostMapping("/template-list")
    @Operation(summary = "查询模板", description = "根据条件查询模板列表")
    public ZhyPageDTO<SysTemplateDTO> getSysTemplateList(@RequestBody @Valid SysTemplateListQuery query){
        return sysTemplateService.getSysTemplatesByCondition(query);
    }

    @PostMapping("/template-detail")
    @Operation(summary = "模板详情", description = "根据模板id获取模板详情")
    public SysTemplateDTO getSysTemplateDetail(@RequestBody @Valid SysTemplateDetailQuery query){
        return sysTemplateService.getSysTemplateDetailById(new Id(query.getTemplateId()));
    }

    @PostMapping("/add-template")
    @Operation(summary = "新增模板", description = "新增模板")
    public SysTemplateDTO addSysTemplate(@RequestBody @Valid SysTemplateAddCommand command){
        return sysTemplateService.createSysTemplate(command);
    }

    @PostMapping("/update-template")
    @Operation(summary = "修改模板", description = "修改模板")
    public SysTemplateDTO updateSysTemplate(@RequestBody @Valid SysTemplateUpdateCommand command){
        return sysTemplateService.updateSysTemplate(command);
    }

    @PostMapping("/delete-template")
    @Operation(summary = "删除模板", description = "删除模板")
    public boolean deleteSysTemplate(@RequestBody @Valid SysTemplateDetailQuery query){
        return sysTemplateService.deleteSysTemplate(new Id(query.getTemplateId()), new UserId(query.getUserId()));
    }


}
