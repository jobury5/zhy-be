package com.zhy.mapstruct;

import com.zhy.dataobject.StaffDeptDO;
import com.zhy.domain.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentConverter {

    @Mapping(target = "deptId.id", source = "id")
    @Mapping(target = "deptName.name", source = "name")
    @Mapping(target = "hodId.userId", source = "hodId")
    @Mapping(target = "parentId.id", source = "parentId")
    Department toEntity(StaffDeptDO staffDeptDO);

    @Mapping(target = "id", source = "deptId.id")
    @Mapping(target = "name", source = "deptName.name")
    @Mapping(target = "hodId", source = "hodId.userId")
    @Mapping(target = "parentId", source = "parentId.id")
    StaffDeptDO toDO(Department department);

}
