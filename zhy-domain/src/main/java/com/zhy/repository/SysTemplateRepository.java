package com.zhy.repository;

import com.zhy.domain.entity.common.ZhyPage;
import com.zhy.domain.entity.sys.SysTemplate;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.sys.DictValue;
import com.zhy.types.sys.DocType;

public interface SysTemplateRepository {

    SysTemplate find(Id templateId);

    SysTemplate find(DictValue templateType, Id shipownerId);
    ZhyPage<SysTemplate> findByPage(DictValue templateType, Name templateName, Id shipownerId, int pageNo, int pageSize);

    SysTemplate save(Id templateId, DictValue templateType, Name templateName, Id shipownerId, DocType docType, String remark, Id attachId, UserId userId);

    Boolean remove(Id templateId, UserId userId);
}
