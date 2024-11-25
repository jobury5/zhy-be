package com.zhy.mapstruct;


import com.zhy.dataobject.CrewCertReqDO;
import com.zhy.domain.entity.CrewCertReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CrewCertReqConverter {

    @Mapping(target = "crewId.id", source = "crewId")
    @Mapping(target = "certId.id", source = "certId")
    @Mapping(target = "certNumber.certNumber", source = "certNumber")
    @Mapping(target = "authority.authority", source = "authority")
    @Mapping(target = "attachId.id", source = "attachId")
    @Mapping(target = "reqId.id", source = "id")
    CrewCertReq toEntity(CrewCertReqDO crewCertReqDO);


    @Mapping(target = "crewId", source = "crewId.id")
    @Mapping(target = "certId", source = "certId.id")
    @Mapping(target = "certNumber", source = "certNumber.certNumber")
    @Mapping(target = "authority", source = "authority.authority")
    @Mapping(target = "attachId", source = "attachId.id")
    @Mapping(target = "id", source = "reqId.id")
    CrewCertReqDO toDO(CrewCertReq crewCertReq);


}
