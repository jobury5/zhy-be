package com.zhy.application.impl;

import cn.hutool.core.util.StrUtil;
import com.zhy.application.SysTemplateService;
import com.zhy.cqe.sys.SysTemplateAddCommand;
import com.zhy.cqe.sys.SysTemplateListQuery;
import com.zhy.cqe.sys.SysTemplateUpdateCommand;
import com.zhy.domain.entity.common.ZhyPage;
import com.zhy.domain.entity.sys.SysTemplate;
import com.zhy.dto.SysTemplateDTO;
import com.zhy.dto.ZhyPageDTO;
import com.zhy.mapstruct.PageAssembler;
import com.zhy.mapstruct.SysTemplateAssembler;
import com.zhy.repository.SysTemplateRepository;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.sys.DictValue;
import com.zhy.types.sys.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: jobury
 * @Date: 2024/9/20 16:55
 */
@Service
public class SysTemplateServiceImpl implements SysTemplateService {

    @Autowired
    private SysTemplateRepository sysTemplateRepository;

    @Autowired
    private SysTemplateAssembler sysTemplateAssembler;

    @Autowired
    private PageAssembler pageAssembler;

    @Override
    public ZhyPageDTO<SysTemplateDTO> getSysTemplatesByCondition(SysTemplateListQuery query) {
        DictValue templateType = null;
        if(StrUtil.isNotBlank(query.getTemplateType())){
            templateType = new DictValue(query.getTemplateType());
        }
        Name templateName = null;
        if(StrUtil.isNotBlank(query.getTemplateName())){
            templateName = new Name(query.getTemplateName());
        }
        Id shipownerId = Optional.ofNullable(query.getShipownerId()).map(Id::new).orElse(null);
        ZhyPage<SysTemplate> sysTemplatePage = sysTemplateRepository.findByPage(templateType, templateName, shipownerId, query.getPageNo(), query.getPageSize());
        ZhyPageDTO<SysTemplateDTO> zhyPageDTO = pageAssembler.toDTO(sysTemplatePage);
        zhyPageDTO.setList(sysTemplateAssembler.toDTOs(sysTemplatePage.getRecords()));
        return zhyPageDTO;
    }

    @Override
    public SysTemplateDTO getSysTemplateDetailById(Id templateId) {
        SysTemplate sysTemplate = sysTemplateRepository.find(templateId);
        return sysTemplateAssembler.toDTO(sysTemplate);
    }

    @Override
    public Boolean deleteSysTemplate(Id templateId, UserId userId) {
        return sysTemplateRepository.remove(templateId, userId);
    }

    @Override
    public SysTemplateDTO createSysTemplate(SysTemplateAddCommand command) {
        Id shipownerId = Optional.ofNullable(command.getShipownerId()).map(Id::new).orElse(new Id(0L));
        SysTemplate newTemplate = sysTemplateRepository.save(null, new DictValue(command.getTemplateType()), new Name(command.getTemplateName()), shipownerId, new DocType(command.getDocType()), command.getRemark(), new Id(command.getAttachId()), new UserId(command.getUserId()));
        return sysTemplateAssembler.toDTO(newTemplate);
    }

    @Override
    public SysTemplateDTO updateSysTemplate(SysTemplateUpdateCommand command) {
        Id shipownerId = Optional.ofNullable(command.getShipownerId()).map(Id::new).orElse(new Id(0L));
        SysTemplate newTemplate = sysTemplateRepository.save(new Id(command.getTemplateId()), new DictValue(command.getTemplateType()), new Name(command.getTemplateName()), shipownerId, new DocType(command.getDocType()), command.getRemark(), new Id(command.getAttachId()), new UserId(command.getUserId()));
        return sysTemplateAssembler.toDTO(newTemplate);
    }

}
