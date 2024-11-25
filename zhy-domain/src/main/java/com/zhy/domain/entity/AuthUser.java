package com.zhy.domain.entity;

import com.zhy.types.*;
import lombok.Data;

@Data
public class AuthUser {

    private UserId userId;

    private UserName userName;

    private MailAddress email;

    private Password password;

    private AreaNumber areaNumber;

    private Mobile mobile;

    private Id tenantId;

    private UserType userType;

}
