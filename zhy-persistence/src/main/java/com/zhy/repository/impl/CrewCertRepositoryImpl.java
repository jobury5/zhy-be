package com.zhy.repository.impl;

import com.zhy.dataobject.CrewCertDO;
import com.zhy.dataobject.CrewCertReqDO;
import com.zhy.domain.entity.CrewCert;
import com.zhy.domain.entity.CrewCertReq;
import com.zhy.mapper.CrewCertMapper;
import com.zhy.mapper.CrewCertReqMapper;
import com.zhy.mapstruct.CrewCertConverter;
import com.zhy.mapstruct.CrewCertReqConverter;
import com.zhy.repository.CrewCertRepository;
import com.zhy.types.Authority;
import com.zhy.types.CertNumber;
import com.zhy.types.Id;
import com.zhy.types.UserId;
import com.zhy.types.approveflow.BusinessStatus;
import com.zhy.util.ZhyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @Author: jobury
 * @Date: 2024/8/21 14:56
 */

@Repository
public class CrewCertRepositoryImpl implements CrewCertRepository {

    @Autowired
    private CrewCertMapper crewCertMapper;

    @Autowired
    private CrewCertReqMapper crewCertReqMapper;

    @Autowired
    private CrewCertConverter crewCertConverter;

    @Autowired
    private CrewCertReqConverter crewCertReqConverter;

    @Override
    public CrewCert save(Id crewId, Id certId, CertNumber certNumber, Authority authority, Id attachId, UserId userId) {
        CrewCertDO crewCertDO = new CrewCertDO();
        crewCertDO.setCrewId(crewId.getId());
        crewCertDO.setCertId(certId.getId());
        crewCertDO.setCertNumber(certNumber.getCertNumber());
        crewCertDO.setAuthority(authority.getAuthority());
        crewCertDO.setAttachId(attachId.getId());
        crewCertDO.setCreateBy(userId.getUserId());
        LocalDateTime currentTime = ZhyDateUtil.getCurrentTime();
        crewCertDO.setGmtCreate(currentTime);
        crewCertDO.setGmtModify(currentTime);
        crewCertMapper.insert(crewCertDO);
        return crewCertConverter.toEntity(crewCertDO);
    }

    @Override
    public CrewCertReq saveReq(Id crewId, Id certId, CertNumber certNumber, Authority authority, Id attachId, UserId userId) {
        CrewCertReqDO crewCertReqDO = new CrewCertReqDO();
        crewCertReqDO.setCrewId(crewId.getId());
        crewCertReqDO.setCertId(certId.getId());
        crewCertReqDO.setCertNumber(certNumber.getCertNumber());
        crewCertReqDO.setAuthority(authority.getAuthority());
        crewCertReqDO.setAttachId(attachId.getId());
        crewCertReqDO.setStatus(BusinessStatus.NEW.getValue());
        crewCertReqDO.setCreateBy(userId.getUserId());
        LocalDateTime currentTime = ZhyDateUtil.getCurrentTime();
        crewCertReqDO.setGmtCreate(currentTime);
        crewCertReqDO.setGmtModify(currentTime);
        crewCertReqMapper.insert(crewCertReqDO);
        return crewCertReqConverter.toEntity(crewCertReqDO);
    }

    @Override
    public CrewCert sync(Id crewReqId, UserId userId) {
        CrewCert crewCert = new CrewCert();
        CrewCertReqDO crewCertReqDO = crewCertReqMapper.selectById(crewReqId.getId());
        if(crewCertReqDO != null){
            CrewCertDO crewCertDO = new CrewCertDO();
            crewCertDO.setCrewId(crewCertReqDO.getCrewId());
            crewCertDO.setCertId(crewCertReqDO.getCertId());
            crewCertDO.setCertNumber(crewCertReqDO.getCertNumber());
            crewCertDO.setAuthority(crewCertReqDO.getAuthority());
            crewCertDO.setAttachId(crewCertReqDO.getAttachId());
            crewCertDO.setCreateBy(userId.getUserId());
            LocalDateTime currentTime = ZhyDateUtil.getCurrentTime();
            crewCertDO.setGmtCreate(currentTime);
            crewCertDO.setGmtModify(currentTime);
            crewCertMapper.insert(crewCertDO);
            crewCert = crewCertConverter.toEntity(crewCertDO);
        }
        return crewCert;
    }

    @Override
    public void changeStatus(Id certReqId, BusinessStatus status, UserId userId) {
        CrewCertReqDO crewCertReqDO = new CrewCertReqDO();
        crewCertReqDO.setId(certReqId.getId());
        crewCertReqDO.setGmtModify(ZhyDateUtil.getCurrentTime());
        crewCertReqDO.setModifyBy(userId.getUserId());
        crewCertReqMapper.updateById(crewCertReqDO);
    }

}
