package com.zhy.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: jobury
 * @Date: 2024/9/20 16:34
 */

@Data
public class SysTemplateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long templateId;

    private String templateTypeName;

    private String templateName;

    private String shipownerName;

    private Integer version;

    private Long attachId;

}
