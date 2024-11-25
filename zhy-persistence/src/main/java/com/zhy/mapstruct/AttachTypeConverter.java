package com.zhy.mapstruct;

import com.zhy.dataobject.SysAttachTypeDO;
import com.zhy.domain.entity.AttachType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttachTypeConverter {

    @Mapping(target = "attachTypeId.id", source = "id")
    @Mapping(target = "type.dirName", source = "type")
    @Mapping(target = "subType.dirName", source = "subType")
    AttachType toEntity(SysAttachTypeDO sysAttachTypeDO);

    @Mapping(target = "id", source = "attachTypeId.id")
    @Mapping(target = "type", source = "type.dirName")
    @Mapping(target = "subType", source = "subType.dirName")
    SysAttachTypeDO toDO(AttachType attachType);

}
