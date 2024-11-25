package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: jobury
 * @Date: 2024/8/19 16:03
 */

@Data
public class BaseDO  {

    @TableField("is_valid")
    private Boolean valid;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;


    private Long createBy;
    /**
     * 更新时间
     */
    private LocalDateTime gmtModify;

    private Long modifyBy;

}
