package com.zhy.mapstruct;

import com.zhy.domain.entity.sys.SysCert;
import com.zhy.dto.SysCertDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SysCertAssembler {

    @Mapping(target = "certId.id", source = "certId")
    @Mapping(target = "certType.dictLabel.name", source = "certTypeName")
    @Mapping(target = "certType.dictLabelEn.name", source = "certTypeEnName")
    @Mapping(target = "certName.name", source = "certName")
    @Mapping(target = "certEnName.name", source = "certEnName")
    @Mapping(target = "parentSysCert.certName.name", source = "parentCertName")
    @Mapping(target = "parentSysCert.certEnName.name", source = "parentCertEnName")
    @Mapping(target = "simpleCode.code", source = "simpleCode")
    SysCert toEntity(SysCertDTO sysCertDTO);

    @Mapping(target = "certId", source = "certId.id")
    @Mapping(target = "certTypeName", source = "certType.dictLabel.name")
    @Mapping(target = "certTypeEnName", source = "certType.dictLabelEn.name")
    @Mapping(target = "certName", source = "certName.name")
    @Mapping(target = "certEnName", source = "certEnName.name")
    @Mapping(target = "parentCertName", source = "parentSysCert.certName.name")
    @Mapping(target = "parentCertEnName", source = "parentSysCert.certEnName.name")
    @Mapping(target = "simpleCode", source = "simpleCode.code")
    SysCertDTO toDTO(SysCert sysCert);

    List<SysCert> toEntities(List<SysCertDTO> sysCertDTOs);

    List<SysCertDTO> toDTOs(List<SysCert> sysCerts);




}
