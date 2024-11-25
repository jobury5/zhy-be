package com.zhy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhy.dataobject.SysDictDataDO;
import com.zhy.domain.entity.sys.DictData;
import com.zhy.mapper.SysDictDataMapper;
import com.zhy.mapstruct.DictDataConverter;
import com.zhy.repository.SysDictRepository;
import com.zhy.types.sys.DictType;
import com.zhy.types.sys.DictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/18 16:37
 */

@Repository
public class SysDictRepositoryImpl implements SysDictRepository {

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private DictDataConverter dictDataConverter;

    @Override
    public List<DictData> find(DictType dictType) {
        LambdaQueryWrapper<SysDictDataDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysDictDataDO::getDictType, dictType.getType());
        queryWrapper.eq(SysDictDataDO::getValid, true);
        queryWrapper.orderByAsc(SysDictDataDO::getDictSort);
        List<SysDictDataDO> sysDictDataDOs = sysDictDataMapper.selectList(queryWrapper);
        return dictDataConverter.toEntities(sysDictDataDOs);
    }

    @Override
    public DictData find(DictType dictType, DictValue dictValue) {
        LambdaQueryWrapper<SysDictDataDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysDictDataDO::getDictType, dictType.getType());
        queryWrapper.eq(SysDictDataDO::getDictValue, dictValue.getValue());
        SysDictDataDO sysDictDataDO = sysDictDataMapper.selectOne(queryWrapper);
        return dictDataConverter.toEntity(sysDictDataDO);
    }

}
