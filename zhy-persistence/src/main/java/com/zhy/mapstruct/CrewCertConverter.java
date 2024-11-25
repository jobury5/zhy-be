package com.zhy.mapstruct;

import com.zhy.dataobject.CrewCertDO;
import com.zhy.domain.entity.CrewCert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CrewCertConverter {

    @Mapping(target = "crewId.id", source = "crewId")
    @Mapping(target = "certId.id", source = "certId")
    @Mapping(target = "certNumber.certNumber", source = "certNumber")
    @Mapping(target = "authority.authority", source = "authority")
    @Mapping(target = "attachId.id", source = "attachId")
    CrewCert toEntity(CrewCertDO crewCertDO);


    @Mapping(target = "crewId", source = "crewId.id")
    @Mapping(target = "certId", source = "certId.id")
    @Mapping(target = "certNumber", source = "certNumber.certNumber")
    @Mapping(target = "authority", source = "authority.authority")
    @Mapping(target = "attachId", source = "attachId.id")
    CrewCertDO toDO(CrewCert crewCert);

}
