package com.zhy.repository;

import com.zhy.domain.entity.CrewCert;
import com.zhy.domain.entity.CrewCertReq;
import com.zhy.types.Authority;
import com.zhy.types.CertNumber;
import com.zhy.types.Id;
import com.zhy.types.UserId;
import com.zhy.types.approveflow.BusinessStatus;

public interface CrewCertRepository {

    CrewCert save(Id crewId, Id certId, CertNumber certNumber, Authority authority, Id attachId, UserId userId);

    CrewCertReq saveReq(Id crewId, Id certId, CertNumber certNumber, Authority authority, Id attachId, UserId userId);

    CrewCert sync(Id crewReqId, UserId userId);

    void changeStatus(Id certReqId, BusinessStatus status, UserId userId);

}
