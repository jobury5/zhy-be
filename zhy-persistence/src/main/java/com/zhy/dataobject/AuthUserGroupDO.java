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
 * @since 2024-08-20
 */
@Getter
@Setter
@TableName("auth_user_group")
public class AuthUserGroupDO extends BaseDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * User Id
     */
    private Long userId;

    /**
     * User Group Id
     */
    private Long groupId;
}
