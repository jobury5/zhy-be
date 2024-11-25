package com.zhy.application.impl;

import com.zhy.application.ApproveFlowService;
import com.zhy.application.CrewCertService;
import com.zhy.domain.entity.AuthUser;
import com.zhy.domain.entity.CrewCertReq;
import com.zhy.dto.CrewCertReqDTO;
import com.zhy.external.OssService;
import com.zhy.mapstruct.CrewCertAssembler;
import com.zhy.repository.CrewCertRepository;
import com.zhy.types.*;
import com.zhy.types.aliyun.OssKey;
import com.zhy.types.approveflow.FlowBusinessKey;
import com.zhy.types.approveflow.FlowInstanceId;
import com.zhy.types.approveflow.FlowProcessUniqueId;
import com.zhy.types.approveflow.FlowUserInfo;
import com.zhy.types.approveflow.BusinessStatus;
import com.zhy.types.approveflow.FlowProcessEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: jobury
 * @Date: 2024/8/21 15:24
 */

@Service
public class CrewCertServiceImpl implements CrewCertService {

    @Autowired
    private ApproveFlowService approveFlowService;

    @Autowired
    private CrewCertRepository crewCertRepository;

    @Autowired
    private CrewCertAssembler crewCertAssembler;

    @Autowired
    private OssService ossService;

    @Override
    public CrewCertReqDTO applyCert(Id crewId, Id certId, CertNumber certNumber, Authority authority, Id attachId, AuthUser authUser) {
        CrewCertReqDTO crewCertReqDTO = new CrewCertReqDTO();
        CrewCertReq crewCertReq = crewCertRepository.saveReq(crewId, certId, certNumber, authority, attachId, authUser.getUserId());
        if(crewCertReq != null && crewCertReq.getReqId() != null) {
            String businessKey = crewCertReq.getReqId().getId().toString();
            FlowProcessUniqueId flowProcessUniqueId = new FlowProcessUniqueId(FlowProcessEnum.CERT_APPLY);
            FlowUserInfo flowUserInfo = new FlowUserInfo(authUser.getUserId().getUserId().toString(), authUser.getUserName().getUserName());
            FlowBusinessKey flowBusinessKey = new FlowBusinessKey(businessKey);
            FlowInstanceId flowInstanceId = approveFlowService.startFlow(flowProcessUniqueId, flowUserInfo, flowBusinessKey);
            crewCertReqDTO = crewCertAssembler.toDTO(crewCertReq);
            crewCertReqDTO.setInstanceId(flowInstanceId.getInstanceId());
        }
        return crewCertReqDTO;
    }

    @Override
    @Transactional
    public Boolean approveCert(Id certReqId, FlowInstanceId flowInstanceId, AuthUser authUser) {
        FlowUserInfo flowUserInfo = new FlowUserInfo(authUser.getUserId().getUserId().toString(), authUser.getUserName().getUserName());
        approveFlowService.executeFlowTask(flowInstanceId, flowUserInfo);
        boolean isApproved = approveFlowService.instanceApproved(flowInstanceId);
        if (isApproved) {
            crewCertRepository.changeStatus(certReqId, BusinessStatus.PASS, authUser.getUserId());
            crewCertRepository.sync(certReqId, authUser.getUserId());
        }
        return true;
    }

    @Override
    public String getCertFileUrl(String fileKey) {
        Url signedUrl = ossService.getSignedUrl(new OssKey(fileKey));
        return signedUrl.toString();
    }

}
