package com.zhy.domain.entity;

import com.zhy.types.Authority;
import com.zhy.types.CertNumber;
import com.zhy.types.Id;
import com.zhy.types.approveflow.BusinessStatus;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/8/21 14:45
 */

@Data
public class CrewCertReq {

    private Id reqId;

    private Id crewId;

    private Id certId;

    private CertNumber certNumber;

    private Authority authority;

    private Id attachId;

    private BusinessStatus businessStatus;

}
