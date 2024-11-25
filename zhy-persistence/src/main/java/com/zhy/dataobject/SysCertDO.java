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
 * @since 2024-09-18
 */
@Getter
@Setter
@TableName("sys_cert")
public class SysCertDO extends BaseDO {

    /**
     * System certificate id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Certificate type(sys_dict_data.dict_value)
     */
    private String certType;

    /**
     * Certificate name
     */
    private String name;

    /**
     * Certificate English name
     */
    private String enName;

    /**
     * Certificate simple code
     */
    private String simpleCode;

    /**
     * Alibaba Cloud template id
     */
    private Long aliTemplateId;

    /**
     * Certificate parent id
     */
    private Long parentId;

    /**
     * has sub certificates,0-no,1-yes
     */
    private Boolean hasChild;

    /**
     * sequence number
     */
    private Integer sort;

    /**
     * Days of advance warning
     */
    private Integer warnDays;

    /**
     * Certificate valid years
     */
    private Integer validYears;
}
