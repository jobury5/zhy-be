package com.zhy.repository;

import com.zhy.domain.entity.common.ZhyPage;
import com.zhy.domain.entity.sys.SysCert;
import com.zhy.types.Code;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.sys.DictValue;

import java.util.List;

public interface SysCertRepository {

    SysCert find(Id certId);
    SysCert find(Code simpleCode);
    List<SysCert> find(DictValue certType, Name certName);
    ZhyPage<SysCert> findByPage(DictValue certType, Name certName, int pageNo, int pageSize);
    SysCert save(Id certId, DictValue certType, Id parentCertId, Name certName, Name certEnName, Code simpleCode, Integer validYears, Integer warnDays, UserId userId);
    Boolean remove(Id certId, UserId userId);
}
