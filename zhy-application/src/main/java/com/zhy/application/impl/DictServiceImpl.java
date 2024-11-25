package com.zhy.application.impl;

import com.zhy.application.DictService;
import com.zhy.domain.entity.sys.DictData;
import com.zhy.dto.DictDataDTO;
import com.zhy.mapstruct.DictDataAssembler;
import com.zhy.repository.SysDictRepository;
import com.zhy.types.sys.DictType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/18 16:55
 */

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private SysDictRepository sysDictRepository;

    @Autowired
    private DictDataAssembler assembler;

    @Override
    public List<DictDataDTO> getDictDatasByType(DictType dictType) {
        List<DictData> dictDatas = sysDictRepository.find(dictType);
        return assembler.toDTOs(dictDatas);
    }




}
