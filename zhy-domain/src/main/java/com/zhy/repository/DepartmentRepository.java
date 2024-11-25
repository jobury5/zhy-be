package com.zhy.repository;

import com.zhy.domain.entity.Department;
import com.zhy.types.Id;
import com.zhy.types.Name;

public interface DepartmentRepository {

    Department find(Id deptId);

    Department find(Name deptName);

}
