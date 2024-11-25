package com.zhy.mapstruct;

import com.zhy.dataobject.SysCertDO;
import com.zhy.domain.entity.sys.SysCert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysCertConverter {

    @Mapping(target = "certId.id", source = "id")
    @Mapping(target = "certType.dictValue.value", source = "certType")
    @Mapping(target = "certName.name", source = "name")
    @Mapping(target = "certEnName.name", source = "enName")
    @Mapping(target = "simpleCode.code", source = "simpleCode")
    @Mapping(target = "parentSysCert.certId.id", source = "parentId")
    SysCert toEntity(SysCertDO sysCertDO);

    @Mapping(target = "id", source = "certId.id")
    @Mapping(target = "certType", source = "certType.dictValue.value")
    @Mapping(target = "name", source = "certName.name")
    @Mapping(target = "enName", source = "certEnName.name")
    @Mapping(target = "simpleCode", source = "simpleCode.code")
    @Mapping(target = "parentId", source = "parentSysCert.certId.id")
    SysCertDO toDO(SysCert sysCert);


    List<SysCert> toEntities(List<SysCertDO> sysCertDOs);

    List<SysCertDO> toDOs(List<SysCert> sysCerts);

}
