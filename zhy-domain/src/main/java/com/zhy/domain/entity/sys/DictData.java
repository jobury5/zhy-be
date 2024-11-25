package com.zhy.domain.entity.sys;

import com.zhy.types.Name;
import com.zhy.types.sys.DictType;
import com.zhy.types.sys.DictValue;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/9/18 16:29
 */

@Data
public class DictData {

    private DictType dictType;

    private Name dictLabel;

    private Name dictLabelEn;

    private DictValue dictValue;



}
