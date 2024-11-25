package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("sys_dict_data")
public class SysDictDataDO extends BaseDO {

    /**
     * System dictionary data id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * dictionary type
     */
    private Integer dictSort;

    /**
     * dictionary label(chinese)
     */
    private String dictLabel;

    /**
     * dictionary label(English)
     */
    private String dictLabelEn;

    /**
     * dictionary value
     */
    private String dictValue;

    /**
     * dictionary type
     */
    private String dictType;

    /**
     * css class
     */
    private String cssClass;

    /**
     * list display style
     */
    private String listClass;

    /**
     * Flag readonly mode ( 0 - no , 1 - yes ) default 0
     */
    private Integer isReadonly;

    /**
     * is default value (0-no,1-yes)
     */
    @TableField("is_default_value")
    private Boolean defaultValue;

    /**
     * remark
     */
    private String remark;

    private String oldValue;
}
