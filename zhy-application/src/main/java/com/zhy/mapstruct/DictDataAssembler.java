package com.zhy.mapstruct;

import com.zhy.domain.entity.sys.DictData;
import com.zhy.dto.DictDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictDataAssembler {

    @Mapping(target = "dictType.type", source = "dictType")
    @Mapping(target = "dictLabel.name", source = "dictLabel")
    @Mapping(target = "dictLabelEn.name", source = "dictLabelEn")
    @Mapping(target = "dictValue.value", source = "dictValue")
    DictData toEntity(DictDataDTO dictDataDTO);

    @Mapping(target = "dictType", source = "dictType.type")
    @Mapping(target = "dictLabel", source = "dictLabel.name")
    @Mapping(target = "dictLabelEn", source = "dictLabelEn.name")
    @Mapping(target = "dictValue", source = "dictValue.value")
    DictDataDTO toDTO(DictData dictData);

    List<DictData> toEntities(List<DictDataDTO> dictDataDTOs);

    List<DictDataDTO> toDTOs(List<DictData> dictDatas);

}
