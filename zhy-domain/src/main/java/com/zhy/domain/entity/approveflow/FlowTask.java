package com.zhy.domain.entity.approveflow;

import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.approveflow.FlowBusinessKey;
import com.zhy.types.approveflow.FlowProcessEnum;
import lombok.Data;

/**
 * @Author: jobury
 * @Date: 2024/8/28 16:40
 */

@Data
public class FlowTask {

    private FlowProcessEnum flowProcess;

    private Id taskId;

    private Name taskName;

    private Id instanceId;

    private FlowBusinessKey businessKey;

}
