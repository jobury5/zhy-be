package com.zhy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthCodeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Integer areaNumber;

    private String mobile;

    private String authCode;

}