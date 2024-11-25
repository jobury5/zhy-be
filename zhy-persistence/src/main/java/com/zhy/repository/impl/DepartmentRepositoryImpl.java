package com.zhy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhy.dataobject.StaffDeptDO;
import com.zhy.domain.entity.Department;
import com.zhy.mapper.StaffDeptMapper;
import com.zhy.mapstruct.DepartmentConverter;
import com.zhy.repository.DepartmentRepository;
import com.zhy.types.Id;
import com.zhy.types.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: jobury
 * @Date: 2024/8/20 18:08
 */

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    private StaffDeptMapper staffDeptMapper;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Override
    public Department find(Id deptId) {
        StaffDeptDO staffDeptDO = staffDeptMapper.selectById(deptId.getId());
        return departmentConverter.toEntity(staffDeptDO);
    }

    @Override
    public Department find(Name deptName) {
        LambdaQueryWrapper<StaffDeptDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StaffDeptDO::getName, deptName.getName());
        StaffDeptDO staffDeptDO = staffDeptMapper.selectOne(queryWrapper);
        return departmentConverter.toEntity(staffDeptDO);
    }
}
