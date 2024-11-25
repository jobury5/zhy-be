package com.zhy.domain.entity;

import com.zhy.types.Name;
import com.zhy.types.auth.BtnPerm;
import com.zhy.types.auth.UrlPerm;
import com.zhy.types.Id;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/10/9 14:59
 */
@Data
public class AuthPerm {

    private Name name;

    private Id menuId;

    private UrlPerm urlPerm;

    private BtnPerm btnPerm;



}
