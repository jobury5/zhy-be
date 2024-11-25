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
 * @since 2024-09-20
 */
@Getter
@Setter
@TableName("sys_template")
public class SysTemplateDO extends BaseDO {

    /**
     * Template id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Template type,1-contract,2-resume,3-email
     */
    private String type;

    /**
     * Template name
     */
    private String name;

    /**
     * shipowner id
     */
    private Long shipownerId;

    /**
     * Version
     */
    private Integer version;

    /**
     * Document type,1-docx,2-xlsx,3-txt
     */
    private String docType;

    /**
     * Remark of template
     */
    private String remark;

    /**
     * Attachment id
     */
    private Long attachId;
}
