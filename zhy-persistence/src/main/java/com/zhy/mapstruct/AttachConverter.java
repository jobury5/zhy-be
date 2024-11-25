package com.zhy.mapstruct;

import com.zhy.dataobject.AliAttachDO;
import com.zhy.domain.entity.Attach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttachConverter {

    @Mapping(target = "attachId.id", source = "id")
    @Mapping(target = "fileName.fileName", source = "fileName")
    @Mapping(target = "fileSize.size", source = "fileSize")
    @Mapping(target = "originalFileName.name", source = "originalFileName")
    @Mapping(target = "contentType.contentType", source = "contentType")
    @Mapping(target = "fileExtension.extension", source = "fileExtension")
    Attach toEntity(AliAttachDO aliAttachDO);


    @Mapping(target = "id", source = "attachId.id")
    @Mapping(target = "fileName", source = "fileName.fileName")
    @Mapping(target = "fileSize", source = "fileSize.size")
    @Mapping(target = "originalFileName", source = "originalFileName.name")
    @Mapping(target = "contentType", source = "contentType.contentType")
    @Mapping(target = "fileExtension", source = "fileExtension.extension")
    AliAttachDO toDO(Attach attach);

    List<Attach> toEntities(List<AliAttachDO> aliAttachDOs);

    List<AliAttachDO> toDOs(List<Attach> attachs);

}
