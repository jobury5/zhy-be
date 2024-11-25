package com.zhy.mapper;

import com.zhy.dataobject.AuthUserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobury
 * @since 2024-10-09
 */
@Mapper
public interface AuthUserRoleMapper extends BaseMapper<AuthUserRoleDO> {

    List<AuthUserRoleDO> selectByCon(Long userId, Long id);
}
