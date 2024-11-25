package com.zhy.repository.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhy.dataobject.SysCertDO;
import com.zhy.domain.entity.common.ZhyPage;
import com.zhy.domain.entity.sys.DictData;
import com.zhy.domain.entity.sys.SysCert;
import com.zhy.mapper.SysCertMapper;
import com.zhy.mapstruct.PageConverter;
import com.zhy.mapstruct.SysCertConverter;
import com.zhy.repository.SysCertRepository;
import com.zhy.repository.SysDictRepository;
import com.zhy.types.Code;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.sys.DictType;
import com.zhy.types.sys.DictTypeEnum;
import com.zhy.types.sys.DictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/19 11:00
 */

@Repository
public class SysCertRepositoryImpl implements SysCertRepository {

    @Autowired
    private SysCertMapper sysCertMapper;

    @Autowired
    private SysCertConverter sysCertConverter;

    @Autowired
    private PageConverter pageConverter;

    @Autowired
    private SysDictRepository sysDictRepository;

    @Override
    public SysCert find(Id certId) {
        SysCertDO sysCertDO = sysCertMapper.selectById(certId.getId());
        SysCert sysCert = sysCertConverter.toEntity(sysCertDO);
        sysCert.setCertType(sysDictRepository.find(new DictType(DictTypeEnum.CERT_TYPE.getDictType()), new DictValue(sysCertDO.getCertType())));
        if (sysCertDO.getParentId() > 0L) {
            sysCert.setParentSysCert(find(new Id(sysCertDO.getParentId())));
        }
        return sysCert;
    }

    @Override
    public SysCert find(Code simpleCode) {
        LambdaQueryWrapper<SysCertDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysCertDO::getSimpleCode, simpleCode.getCode());
        SysCertDO sysCertDO = sysCertMapper.selectOne(queryWrapper);
        SysCert sysCert = sysCertConverter.toEntity(sysCertDO);
        sysCert.setCertType(sysDictRepository.find(new DictType(DictTypeEnum.CERT_TYPE.getDictType()), new DictValue(sysCertDO.getCertType())));
        if (sysCertDO.getParentId() > 0L) {
            sysCert.setParentSysCert(find(new Id(sysCertDO.getParentId())));
        }
        return sysCertConverter.toEntity(sysCertDO);
    }

    @Override
    public List<SysCert> find(DictValue certType, Name certName) {
        LambdaQueryWrapper<SysCertDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysCertDO::getValid, true);
        queryWrapper.eq(SysCertDO::getHasChild, 0);
        //certType和certName为可选条件
        if (certType != null) {
            queryWrapper.eq(SysCertDO::getCertType, certType.getValue());
        }
        if (certName != null) {
            queryWrapper.like(SysCertDO::getName, certName.getName());
        }
        queryWrapper.orderByAsc(SysCertDO::getSort);
        List<SysCertDO> sysCertDOs = sysCertMapper.selectList(queryWrapper);
        return sysCertConverter.toEntities(sysCertDOs);
    }

    @Override
    public ZhyPage<SysCert> findByPage(DictValue certType, Name certName, int pageNo, int pageSize) {
        LambdaQueryWrapper<SysCertDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysCertDO::getValid, true);
        //queryWrapper.eq(SysCertDO::getHasChild, false);
        //certType为可选条件
        if (certType != null) {
            queryWrapper.eq(SysCertDO::getCertType, certType.getValue());
        }
        // certName为可选条件
        if (certName != null) {
            // 连接多个条件
            queryWrapper.and(w -> w
                    .like(SysCertDO::getName, certName.getName())
                    .or()
                    .like(SysCertDO::getEnName, certName.getName())
                    .or()
                    .like(SysCertDO::getSimpleCode, certName.getName()));
        }
        queryWrapper.orderByAsc(SysCertDO::getCertType, SysCertDO::getSort);
        Page<SysCertDO> pageParam = new Page<>(pageNo, pageSize);
        Page<SysCertDO> sysCertDOPage = sysCertMapper.selectPage(pageParam, queryWrapper);
        ZhyPage<SysCert> zhyPage = pageConverter.toEntity(sysCertDOPage);
        List<SysCert> sysCerts = sysCertConverter.toEntities(sysCertDOPage.getRecords());
        //填充父证书和证书类型字典值
        sysCerts.forEach(c -> {
            if (c.getParentSysCert() != null && c.getParentSysCert().getCertId().getId() > 0L) {
                SysCert parent = find(c.getParentSysCert().getCertId());
                c.setParentSysCert(parent);
            }
            DictData dictData = sysDictRepository.find(new DictType(DictTypeEnum.CERT_TYPE.getDictType()), c.getCertType().getDictValue());
            if (dictData != null) {
                c.setCertType(dictData);
            }
        });
        zhyPage.setRecords(sysCerts);
        return zhyPage;
    }

    @Override
    public SysCert save(Id certId, DictValue certType, Id parentCertId, Name certName, Name certEnName, Code simpleCode, Integer validYears, Integer warnDays, UserId userId) {
        SysCertDO sysCertDO = new SysCertDO();
        sysCertDO.setCertType(certType.getValue());
        if (parentCertId != null) {
            sysCertDO.setParentId(parentCertId.getId());
        }
        sysCertDO.setName(certName.getName());
        if (certEnName != null) {
            sysCertDO.setEnName(certEnName.getName());
        }
        sysCertDO.setSimpleCode(simpleCode.getCode());
        sysCertDO.setValidYears(validYears);
        sysCertDO.setWarnDays(warnDays);
        //更新操作
        if (certId != null) {
            sysCertDO.setId(certId.getId());
            sysCertDO.setModifyBy(userId.getUserId());
            sysCertDO.setGmtModify(LocalDateTimeUtil.now());
            sysCertMapper.updateById(sysCertDO);
        }
        //插入操作
        else {
            sysCertDO.setCreateBy(userId.getUserId());
            sysCertDO.setGmtCreate(LocalDateTimeUtil.now());
            sysCertMapper.insert(sysCertDO);
        }
        SysCert sysCert = sysCertConverter.toEntity(sysCertDO);
        sysCert.setCertType(sysDictRepository.find(new DictType(DictTypeEnum.CERT_TYPE.getDictType()), new DictValue(sysCertDO.getCertType())));
        if (sysCertDO.getParentId() > 0L) {
            sysCert.setParentSysCert(find(new Id(sysCertDO.getParentId())));
        }
        return sysCert;
    }

    @Override
    public Boolean remove(Id certId, UserId userId) {
        SysCertDO sysCertDO = new SysCertDO();
        sysCertDO.setId(certId.getId());
        sysCertDO.setValid(false);
        sysCertDO.setModifyBy(userId.getUserId());
        sysCertDO.setGmtModify(LocalDateTimeUtil.now());
        int i = sysCertMapper.updateById(sysCertDO);
        return i > 0;
    }


}
