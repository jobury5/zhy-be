package com.zhy.repository;

import com.zhy.domain.entity.AuthUser;
import com.zhy.types.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobury
 * @since 2024-02-06
 */
public interface AuthUserRepository {

    AuthUser find(UserId userId);

    AuthUser find(UserName userName);

    AuthUser findGeneral(UserName userName);

    AuthUser find(MailAddress email);

    AuthUser find(AreaNumber areaNumber, Mobile mobile);

}
