package com.zhy.mapstruct;

import com.zhy.dataobject.SysTemplateDO;
import com.zhy.domain.entity.sys.SysTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysTemplateConverter {

    @Mapping(target = "templateId.id", source = "id")
    @Mapping(target = "templateType.dictValue.value", source = "type")
    @Mapping(target = "templateName.name", source = "name")
    @Mapping(target = "shipownerId.id", source = "shipownerId")
    @Mapping(target = "version.version", source = "version")
    @Mapping(target = "docType.type", source = "docType")
    @Mapping(target = "attachId.id", source = "attachId")
    SysTemplate toEntity(SysTemplateDO sysTemplateDO);

    @Mapping(target = "id", source = "templateId.id")
    @Mapping(target = "type", source = "templateType.dictValue.value")
    @Mapping(target = "name", source = "templateName.name")
    @Mapping(target = "shipownerId", source = "shipownerId.id")
    @Mapping(target = "version", source = "version.version")
    @Mapping(target = "docType", source = "docType.type")
    @Mapping(target = "attachId", source = "attachId.id")
    SysTemplateDO toDO(SysTemplate sysTemplate);

    List<SysTemplate> toEntities(List<SysTemplateDO> sysTemplateDOs);

    List<SysTemplateDO> toDOs(List<SysTemplate> sysTemplates);

}
