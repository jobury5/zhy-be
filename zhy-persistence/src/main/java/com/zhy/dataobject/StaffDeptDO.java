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
 * @since 2024-08-20
 */
@Getter
@Setter
@TableName("staff_dept")
public class StaffDeptDO extends BaseDO {

    /**
     * Staff department id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Name of Department
     */
    private String name;

    /**
     * Department HOD id
     */
    private Long hodId;

    /**
     * Department parent id
     */
    private Integer parentId;

    /**
     * location of current department, from dictionary data
     */
    private String location;

    /**
     * 0-only view data of current dept, 1-can view data of all dept
     */
    private Boolean viewFlag;
}
