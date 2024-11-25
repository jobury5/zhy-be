package com.zhy.domain.entity.sys;

import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.Version;
import com.zhy.types.sys.DocType;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/20 14:49
 */

@Data
public class SysTemplate {

    private Id templateId;

    private DictData templateType;

    private Name templateName;

    private Id shipownerId;

    private Name shipownerName;

    private Version version;

    private DocType docType;

    private Id attachId;

    private String remark;

}
