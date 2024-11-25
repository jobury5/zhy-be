package com.zhy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.dataobject.AuthPermissionDO;
import com.zhy.dataobject.AuthUrlPermRolesDO;
import com.zhy.dataobject.AuthUserPermDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限管理 Mapper 接口
 * </p>
 *
 * @author jobury
 * @since 2024-10-08
 */
@Mapper
public interface AuthPermissionMapper extends BaseMapper<AuthPermissionDO> {

    List<AuthUrlPermRolesDO> getUrlPermRoles();

    List<AuthUserPermDO> getUserPermission(Long userId);

}