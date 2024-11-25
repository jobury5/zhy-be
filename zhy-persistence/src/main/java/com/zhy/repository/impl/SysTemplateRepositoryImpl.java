package com.zhy.repository.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhy.dataobject.SysTemplateDO;
import com.zhy.domain.entity.common.ZhyPage;
import com.zhy.domain.entity.sys.DictData;
import com.zhy.domain.entity.sys.SysTemplate;
import com.zhy.mapper.SysTemplateMapper;
import com.zhy.mapstruct.PageConverter;
import com.zhy.mapstruct.SysTemplateConverter;
import com.zhy.repository.SysDictRepository;
import com.zhy.repository.SysTemplateRepository;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.sys.DictType;
import com.zhy.types.sys.DictTypeEnum;
import com.zhy.types.sys.DictValue;
import com.zhy.types.sys.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: jobury
 * @Date: 2024/9/20 15:16
 */

@Repository
public class SysTemplateRepositoryImpl implements SysTemplateRepository {

    @Autowired
    private SysDictRepository sysDictRepository;

    @Autowired
    private SysTemplateMapper sysTemplateMapper;

    @Autowired
    private SysTemplateConverter sysTemplateConverter;

    @Autowired
    private PageConverter pageConverter;

    @Override
    public SysTemplate find(Id templateId) {
        SysTemplateDO sysTemplateDO = sysTemplateMapper.selectById(templateId.getId());
        SysTemplate sysTemplate = sysTemplateConverter.toEntity(sysTemplateDO);
        sysTemplate.setTemplateType(sysDictRepository.find(new DictType(DictTypeEnum.TEMPLATE_TYPE.getDictType()), new DictValue(sysTemplateDO.getType())));
        return sysTemplate;
    }

    @Override
    public SysTemplate find(DictValue templateType, Id shipownerId) {
        LambdaQueryWrapper<SysTemplateDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysTemplateDO::getType, templateType.getValue());
        queryWrapper.eq(SysTemplateDO::getShipownerId, shipownerId.getId());
        SysTemplateDO sysTemplateDO = sysTemplateMapper.selectOne(queryWrapper);
        SysTemplate sysTemplate = sysTemplateConverter.toEntity(sysTemplateDO);
        sysTemplate.setTemplateType(sysDictRepository.find(new DictType(DictTypeEnum.TEMPLATE_TYPE.getDictType()), new DictValue(sysTemplateDO.getType())));
        return sysTemplate;
    }

    @Override
    public ZhyPage<SysTemplate> findByPage(DictValue templateType, Name templateName, Id shipownerId, int pageNo, int pageSize) {
        LambdaQueryWrapper<SysTemplateDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysTemplateDO::getValid, true);
        //templateType为可选条件
        Optional.ofNullable(templateType).ifPresent(t -> queryWrapper.eq(SysTemplateDO::getType, t.getValue()));
        //templateName为可选条件
        Optional.ofNullable(templateName).ifPresent(t -> queryWrapper.like(SysTemplateDO::getName, t.getName()));
        //shipownerId为可选条件
        Optional.ofNullable(shipownerId).ifPresent(t -> queryWrapper.eq(SysTemplateDO::getShipownerId, t.getId()));
        queryWrapper.orderByDesc(SysTemplateDO::getId);

        Page<SysTemplateDO> pageParam = new Page<>(pageNo, pageSize);
        Page<SysTemplateDO> sysTemplateDOPage = sysTemplateMapper.selectPage(pageParam, queryWrapper);
        ZhyPage<SysTemplate> zhyPage = pageConverter.toEntity(sysTemplateDOPage);
        List<SysTemplate> sysTemplates = sysTemplateConverter.toEntities(sysTemplateDOPage.getRecords());
        //填充父证书和证书类型字典值
        sysTemplates.forEach(c -> {
            if (c.getShipownerId() != null && c.getShipownerId().getId() > 0L) {
                //to_do 设置shipowner name
            }
            DictData dictData = sysDictRepository.find(new DictType(DictTypeEnum.TEMPLATE_TYPE.getDictType()), c.getTemplateType().getDictValue());
            if (dictData != null) {
                c.setTemplateType(dictData);
            }
        });
        zhyPage.setRecords(sysTemplates);
        return zhyPage;
    }

    @Override
    public SysTemplate save(Id templateId, DictValue templateType, Name templateName, Id shipownerId, DocType docType, String remark, Id attachId, UserId userId) {
        SysTemplateDO sysTemplateDO = new SysTemplateDO();
        sysTemplateDO.setType(templateType.getValue());
        sysTemplateDO.setName(templateName.getName());
        sysTemplateDO.setShipownerId(shipownerId.getId());
        sysTemplateDO.setDocType(docType.getType());
        sysTemplateDO.setAttachId(attachId.getId());
        sysTemplateDO.setRemark(remark);
        //更新操作
        if (templateId != null) {
            //版本+1
            SysTemplateDO oldTeamplate = sysTemplateMapper.selectById(templateId.getId());
            if(oldTeamplate != null){
                sysTemplateDO.setVersion(oldTeamplate.getVersion() + 1);
            }
            sysTemplateDO.setId(templateId.getId());
            sysTemplateDO.setModifyBy(userId.getUserId());
            sysTemplateDO.setGmtModify(LocalDateTimeUtil.now());
            sysTemplateMapper.updateById(sysTemplateDO);
        }
        //插入操作
        else {
            sysTemplateDO.setVersion(0);
            sysTemplateDO.setCreateBy(userId.getUserId());
            sysTemplateDO.setGmtCreate(LocalDateTimeUtil.now());
            sysTemplateMapper.insert(sysTemplateDO);
        }
        SysTemplate sysTemplate = sysTemplateConverter.toEntity(sysTemplateDO);
        sysTemplate.setTemplateType(sysDictRepository.find(new DictType(DictTypeEnum.TEMPLATE_TYPE.getDictType()), new DictValue(sysTemplateDO.getType())));
        return sysTemplate;
    }

    @Override
    public Boolean remove(Id templateId, UserId userId) {
        SysTemplateDO sysTemplateDO = new SysTemplateDO();
        sysTemplateDO.setId(templateId.getId());
        sysTemplateDO.setValid(false);
        sysTemplateDO.setModifyBy(userId.getUserId());
        sysTemplateDO.setGmtModify(LocalDateTimeUtil.now());
        int i = sysTemplateMapper.updateById(sysTemplateDO);
        return i > 0;
    }

}
