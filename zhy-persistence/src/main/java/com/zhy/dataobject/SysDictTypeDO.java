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
 * @since 2024-09-18
 */
@Getter
@Setter
@TableName("sys_dict_type")
public class SysDictTypeDO extends BaseDO {

    /**
     * System dictionary type id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * dictionary type
     */
    private String dictType;

    /**
     * dictionary name
     */
    private String dictName;

    /**
     * remark
     */
    private String remark;
}
