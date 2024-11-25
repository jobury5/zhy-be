package com.zhy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: jobury
 * @Date: 2024/8/21 15:22
 */

@Data
public class CrewCertReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long reqId;

    private Long crewId;

    private String crewName;

    private Long certId;

    private String certName;

    private String certNumber;

    private String authority;

    private String attachKey;

    private Long instanceId;

}
