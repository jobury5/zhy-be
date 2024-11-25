package com.zhy.application;

import com.zhy.domain.entity.approveflow.FlowInstance;
import com.zhy.domain.entity.approveflow.FlowTask;
import com.zhy.types.approveflow.FlowBusinessKey;
import com.zhy.types.approveflow.FlowInstanceId;
import com.zhy.types.approveflow.FlowProcessUniqueId;
import com.zhy.types.approveflow.FlowUserInfo;

import java.util.List;

public interface ApproveFlowService {

    //启动流程
    FlowInstanceId startFlow(FlowProcessUniqueId flowProcessUniqueId, FlowUserInfo flowUserInfo, FlowBusinessKey flowBusinessKey);

    //执行任务
    void executeFlowTask(FlowInstanceId flowInstanceId, FlowUserInfo flowUserInfo);

    //流程是否审批通过
    boolean instanceApproved(FlowInstanceId flowInstanceId);

    //待审批
    List<FlowTask> getToDoTaskList(FlowUserInfo flowUserInfo);

    //我收到的
    List<FlowTask> getCcTaskList(FlowUserInfo flowUserInfo);

    //已审批
    List<FlowTask> getApprovedTaskList(FlowUserInfo flowUserInfo);

    //我的申请
    List<FlowInstance> getApplyInstanceList(FlowUserInfo flowUserInfo);
}
