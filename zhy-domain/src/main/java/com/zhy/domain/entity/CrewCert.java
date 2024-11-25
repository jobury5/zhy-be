package com.zhy.domain.entity;

import com.zhy.types.Authority;
import com.zhy.types.CertNumber;
import com.zhy.types.Id;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/8/21 14:43
 */

@Data
public class CrewCert {

    private Id crewId;

    private Id certId;

    private CertNumber certNumber;

    private Authority authority;

    private Id attachId;

}
