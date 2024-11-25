package com.zhy.application;

import com.zhy.cqe.sys.SysCertAddCommand;
import com.zhy.cqe.sys.SysCertListQuery;
import com.zhy.cqe.sys.SysCertUpdateCommand;
import com.zhy.dto.SysCertDTO;
import com.zhy.dto.ZhyPageDTO;
import com.zhy.types.Id;
import com.zhy.types.UserId;

public interface SysCertService {

    ZhyPageDTO<SysCertDTO> getSysCertsByCondition(SysCertListQuery sysCertListQuery);

    SysCertDTO getSysCertDetailById(Id certId);

    Boolean deleteSysCert(Id certId, UserId userId);

    SysCertDTO addSysCert(SysCertAddCommand command);

    SysCertDTO updateSysCert(SysCertUpdateCommand command);
}
