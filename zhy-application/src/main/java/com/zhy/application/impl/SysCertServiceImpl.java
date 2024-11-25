package com.zhy.application.impl;

import cn.hutool.core.util.StrUtil;
import com.zhy.application.SysCertService;
import com.zhy.cqe.sys.SysCertAddCommand;
import com.zhy.cqe.sys.SysCertListQuery;
import com.zhy.cqe.sys.SysCertUpdateCommand;
import com.zhy.domain.entity.common.ZhyPage;
import com.zhy.domain.entity.sys.SysCert;
import com.zhy.dto.SysCertDTO;
import com.zhy.dto.ZhyPageDTO;
import com.zhy.mapstruct.PageAssembler;
import com.zhy.mapstruct.SysCertAssembler;
import com.zhy.repository.SysCertRepository;
import com.zhy.repository.SysDictRepository;
import com.zhy.types.Code;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.sys.DictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: jobury
 * @Date: 2024/9/19 11:31
 */

@Service
public class SysCertServiceImpl implements SysCertService {

    @Autowired
    private SysCertRepository sysCertRepository;

    @Autowired
    private SysDictRepository sysDictRepository;

    @Autowired
    private SysCertAssembler sysCertAssembler;

    @Autowired
    private PageAssembler pageAssembler;

    @Override
    public ZhyPageDTO<SysCertDTO> getSysCertsByCondition(SysCertListQuery sysCertListQuery) {
        DictValue certType = null;
        Name certName = null;
        if (StrUtil.isNotBlank(sysCertListQuery.getCertType())) {
            certType = new DictValue(sysCertListQuery.getCertType());
        }
        if (StrUtil.isNotBlank(sysCertListQuery.getCertName())) {
            certName = new Name(sysCertListQuery.getCertName());
        }
        ZhyPage<SysCert> sysCertsPage = sysCertRepository.findByPage(certType, certName, sysCertListQuery.getPageNo(), sysCertListQuery.getPageSize());
        ZhyPageDTO<SysCertDTO> zhyPageDTO = pageAssembler.toDTO(sysCertsPage);
        zhyPageDTO.setList(sysCertAssembler.toDTOs(sysCertsPage.getRecords()));
        return zhyPageDTO;
    }

    @Override
    public SysCertDTO getSysCertDetailById(Id certId) {
        SysCert sysCert = sysCertRepository.find(certId);
        return sysCertAssembler.toDTO(sysCert);
    }

    @Override
    public Boolean deleteSysCert(Id certId, UserId userId) {
        return sysCertRepository.remove(certId, userId);
    }

    @Override
    public SysCertDTO addSysCert(SysCertAddCommand command) {
        Id parentId = Optional.ofNullable(command.getParentCertId()).map(Id::new).orElse(new Id(0L));
        SysCert newSysCert = sysCertRepository.save(null, new DictValue(command.getCertType()), parentId, new Name(command.getCertName()), new Name(command.getCertEnName()), new Code(command.getSimpleCode()), command.getValidYears(), command.getWarnDays(),
                new UserId(command.getUserId()));
        return sysCertAssembler.toDTO(newSysCert);
    }

    @Override
    public SysCertDTO updateSysCert(SysCertUpdateCommand command) {
        Id parentId = Optional.ofNullable(command.getParentCertId()).map(Id::new).orElse(new Id(0L));
        SysCert updateSysCert = sysCertRepository.save(new Id(command.getCertId()), new DictValue(command.getCertType()), parentId, new Name(command.getCertName()), new Name(command.getCertEnName()), new Code(command.getSimpleCode()), command.getValidYears(), command.getWarnDays(),
                new UserId(command.getUserId()));
        return sysCertAssembler.toDTO(updateSysCert);
    }

}
