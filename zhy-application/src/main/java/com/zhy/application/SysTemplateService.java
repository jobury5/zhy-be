package com.zhy.application;

import com.zhy.cqe.sys.SysTemplateAddCommand;
import com.zhy.cqe.sys.SysTemplateListQuery;
import com.zhy.cqe.sys.SysTemplateUpdateCommand;
import com.zhy.dto.SysTemplateDTO;
import com.zhy.dto.ZhyPageDTO;
import com.zhy.types.Id;
import com.zhy.types.UserId;

public interface SysTemplateService {

    ZhyPageDTO<SysTemplateDTO> getSysTemplatesByCondition(SysTemplateListQuery query);

    SysTemplateDTO getSysTemplateDetailById(Id templateId);

    Boolean deleteSysTemplate(Id templateId, UserId userId);

    SysTemplateDTO createSysTemplate(SysTemplateAddCommand command);

    SysTemplateDTO updateSysTemplate(SysTemplateUpdateCommand command);

}
