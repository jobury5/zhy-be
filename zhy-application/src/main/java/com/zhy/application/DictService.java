package com.zhy.application;

import com.zhy.dto.DictDataDTO;
import com.zhy.types.sys.DictType;

import java.util.List;

public interface DictService {

    List<DictDataDTO> getDictDatasByType(DictType dictType) ;



}
