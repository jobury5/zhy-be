package com.zhy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: jobury
 * @Date: 2024/9/18 17:14
 */

@Data
public class DictDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dictType;

    private String dictLabel;

    private String dictLabelEn;

    private String dictValue;

}
