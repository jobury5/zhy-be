package com.zhy.repository;

import com.zhy.domain.entity.sys.DictData;
import com.zhy.types.sys.DictType;
import com.zhy.types.sys.DictValue;

import java.util.List;

public interface SysDictRepository {

    List<DictData> find(DictType dictType);

    DictData find(DictType dictType, DictValue dictValue);

}
