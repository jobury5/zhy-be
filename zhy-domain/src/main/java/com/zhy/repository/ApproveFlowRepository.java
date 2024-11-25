package com.zhy.repository;

import com.zhy.domain.entity.approveflow.FlowInstance;
import com.zhy.domain.entity.approveflow.FlowTask;
import com.zhy.types.UserId;

import java.util.List;

public interface ApproveFlowRepository {

    List<FlowTask> findTodoTasksByUserId(UserId userId);

    List<FlowTask> findCcTasksByUserId(UserId userId);

    List<FlowTask> findHisApprovedTasksByUserId(UserId userId);

    List<FlowInstance> findApplyInstancesByUserId(UserId userId);

}
