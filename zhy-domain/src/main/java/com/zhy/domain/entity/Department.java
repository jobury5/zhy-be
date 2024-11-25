package com.zhy.domain.entity;

import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/8/20 17:25
 */

@Data
public class Department {

    private Id deptId;

    private Name deptName;

    private UserId hodId;

    private Id parentId;

}
