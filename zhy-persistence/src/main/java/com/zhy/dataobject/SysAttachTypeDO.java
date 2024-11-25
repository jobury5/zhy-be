package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobury
 * @since 2024-09-21
 */
@Getter
@Setter
@TableName("sys_attach_type")
public class SysAttachTypeDO extends BaseDO {

    /**
     * System attach type id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * parent type name(finance,work,template)
     */
    private String type;

    /**
     * sub type name
     */
    private String subType;
}
