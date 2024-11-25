package com.zhy.mapstruct;

import com.zhy.domain.entity.CrewCertReq;
import com.zhy.dto.CrewCertReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CrewCertAssembler {

    @Mapping(target = "crewId.id", source = "crewId")
    @Mapping(target = "certId.id", source = "certId")
    @Mapping(target = "certNumber.certNumber", source = "certNumber")
    @Mapping(target = "authority.authority", source = "authority")
    @Mapping(target = "reqId.id", source = "reqId")
    CrewCertReq toEntity(CrewCertReqDTO crewCertReqDTO);

    @Mapping(target = "crewId", source = "crewId.id")
    @Mapping(target = "certId", source = "certId.id")
    @Mapping(target = "certNumber", source = "certNumber.certNumber")
    @Mapping(target = "authority", source = "authority.authority")
    @Mapping(target = "reqId", source = "reqId.id")
    CrewCertReqDTO toDTO(CrewCertReq crewCertReq);



}
