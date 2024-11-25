package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhy.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobury
 * @since 2024-09-25
 */
@Getter
@Setter
@TableName("auth_user")
public class AuthUserDO extends BaseDO {

    /**
     * Auth user id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Username should be unique
     */
    private String userName;

    /**
     * Email should be unique
     */
    private String email;

    /**
     * Password(Encrypted)
     */
    private String password;

    /**
     * Mobile area number
     */
    private Integer areaNumber;

    /**
     * Mobile number
     */
    private String mobile;

    /**
     * Tenant id
     */
    private Integer tenantId;

    /**
     * Type of user (1-Crew, 2-Staff)
     */
    private Integer userType;
}
