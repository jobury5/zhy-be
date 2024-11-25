package com.zhy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: jobury
 * @Date: 2024/9/18 18:15
 */
@Data
public class SysCertDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long certId;

    private String certTypeName;

    private String certTypeEnName;

    private String certName;

    private String certEnName;

    private String parentCertName;

    private String parentCertEnName;

    private String simpleCode;

    private Integer validYears;

    private Integer warnDays;

}
