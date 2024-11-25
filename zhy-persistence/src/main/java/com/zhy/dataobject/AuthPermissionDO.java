package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhy.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限管理
 * </p>
 *
 * @author jobury
 * @since 2024-10-08
 */
@Getter
@Setter
@TableName("auth_permission")
public class AuthPermissionDO extends BaseDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * name
     */
    private String name;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 接口权限标识
     */
    private String urlPerm;

    /**
     * 按钮权限标识
     */
    private String btnPerm;
}
