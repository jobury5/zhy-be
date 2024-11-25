package com.zhy.application;

import com.zhy.domain.entity.AuthUser;
import com.zhy.dto.CrewCertReqDTO;
import com.zhy.types.Authority;
import com.zhy.types.CertNumber;
import com.zhy.types.approveflow.FlowInstanceId;
import com.zhy.types.Id;

public interface CrewCertService {


    CrewCertReqDTO applyCert(Id crewId, Id certId, CertNumber certNumber, Authority authority, Id attachId, AuthUser authUser);

    Boolean approveCert(Id certReqId, FlowInstanceId flowInstanceId, AuthUser authUser);

    String getCertFileUrl(String fileKey);

}
