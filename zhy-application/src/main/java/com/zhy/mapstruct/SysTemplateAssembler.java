package com.zhy.mapstruct;

import com.zhy.domain.entity.sys.SysTemplate;
import com.zhy.dto.SysTemplateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysTemplateAssembler {

    @Mapping(target = "templateId.id", source = "templateId")
    @Mapping(target = "templateType.dictLabel.name", source = "templateTypeName")
    @Mapping(target = "templateName.name", source = "templateName")
    @Mapping(target = "shipownerName.name", source = "shipownerName")
    @Mapping(target = "attachId.id", source = "attachId")
    @Mapping(target = "version.version", source = "version")
    SysTemplate toEntity(SysTemplateDTO sysTemplateDTO);


    @Mapping(target = "templateId", source = "templateId.id")
    @Mapping(target = "templateTypeName", source = "templateType.dictLabel.name")
    @Mapping(target = "templateName", source = "templateName.name")
    @Mapping(target = "shipownerName", source = "shipownerName.name")
    @Mapping(target = "attachId", source = "attachId.id")
    @Mapping(target = "version", source = "version.version")
    SysTemplateDTO toDTO(SysTemplate sysTemplate);

    List<SysTemplate> toEntities(List<SysTemplateDTO> sysTemplateDTO);

    List<SysTemplateDTO> toDTOs(List<SysTemplate> sysTemplates);
}
