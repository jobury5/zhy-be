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
 * @since 2024-10-09
 */
@Getter
@Setter
@TableName("auth_user_role")
public class AuthUserRoleDO extends BaseDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * User id
     */
    private Long userId;

    /**
     * Role id
     */
    private Long roleId;
}
