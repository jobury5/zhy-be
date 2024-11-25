package com.zhy.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/8 18:06
 */
@Data
public class UrlPermRolesDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String urlPerm;

    private List<String> roles;


}
