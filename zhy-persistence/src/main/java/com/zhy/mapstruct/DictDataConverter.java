package com.zhy.mapstruct;

import com.zhy.dataobject.SysDictDataDO;
import com.zhy.domain.entity.sys.DictData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictDataConverter {

    @Mapping(target = "dictType.type", source = "dictType")
    @Mapping(target = "dictLabel.name", source = "dictLabel")
    @Mapping(target = "dictLabelEn.name", source = "dictLabelEn")
    @Mapping(target = "dictValue.value", source = "dictValue")
    DictData toEntity(SysDictDataDO sysDictDataDO);

    @Mapping(target = "dictType", source = "dictType.type")
    @Mapping(target = "dictLabel", source = "dictLabel.name")
    @Mapping(target = "dictLabelEn", source = "dictLabelEn.name")
    @Mapping(target = "dictValue", source = "dictValue.value")
    SysDictDataDO toDO(DictData dictData);



    List<DictData> toEntities(List<SysDictDataDO> sysDictDataDOs);
    List<SysDictDataDO> toDOs(List<DictData> dictDatas);

}
