package com.zhy.domain.entity.approveflow;

import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.UserName;
import com.zhy.types.approveflow.FlowBusinessKey;
import com.zhy.types.approveflow.FlowProcessEnum;
import com.zhy.types.approveflow.FlowTaskKey;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: jobury
 * @Date: 2024/9/9 16:47
 */

@Data
public class FlowInstance {

    private FlowProcessEnum flowProcess;

    private FlowTaskKey currentTaskKey;

    private Name currentTaskName;

    private Id instanceId;

    private FlowBusinessKey businessKey;

    private UserId createUserId;

    private UserName createUserName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long duration;

}
