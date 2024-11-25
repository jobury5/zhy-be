package com.zhy.application.impl;

import com.aizuda.bpm.engine.*;
import com.aizuda.bpm.engine.core.Execution;
import com.aizuda.bpm.engine.core.FlowCreator;
import com.aizuda.bpm.engine.core.enums.InstanceState;
import com.aizuda.bpm.engine.core.enums.NodeSetType;
import com.aizuda.bpm.engine.entity.*;
import com.aizuda.bpm.engine.model.NodeAssignee;
import com.aizuda.bpm.engine.model.NodeModel;
import com.zhy.application.ApproveFlowService;
import com.zhy.domain.entity.AuthUser;
import com.zhy.domain.entity.AuthUserRole;
import com.zhy.domain.entity.Department;
import com.zhy.domain.entity.approveflow.FlowInstance;
import com.zhy.domain.entity.approveflow.FlowTask;
import com.zhy.repository.ApproveFlowRepository;
import com.zhy.repository.AuthUserRepository;
import com.zhy.repository.AuthUserRoleRepository;
import com.zhy.repository.DepartmentRepository;
import com.zhy.types.*;
import com.zhy.types.approveflow.FlowBusinessKey;
import com.zhy.types.approveflow.FlowInstanceId;
import com.zhy.types.approveflow.FlowProcessUniqueId;
import com.zhy.types.approveflow.FlowUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @Author: jobury
 * @Date: 2024/8/19 17:29
 */

@Slf4j
@Service
public class ApproveFlowServiceImpl implements ApproveFlowService {

    @Autowired
    private FlowLongEngine flowLongEngine;

    @Autowired
    private ApproveFlowRepository approveFlowRepository;

    @Autowired
    private AuthUserRoleRepository authUserRoleRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public FlowInstanceId startFlow(FlowProcessUniqueId flowProcessUniqueId, FlowUserInfo flowUserInfo, FlowBusinessKey flowBusinessKey) {
        flowLongEngine.getContext().setTaskActorProvider(
                new TaskActorProvider() {
                    @Override
                    public List<FlwTaskActor> getTaskActors(NodeModel nodeModel, Execution execution) {
                        //节点任务类型都是 1-审批
                        if (nodeModel.getType() != 1) {
                            return null;
                        }
                        //NodeSetType 1:specifyMembers:指定成员, 2:supervisor:主管 3:role:角色
                        // 2-主管
                        List<FlwTaskActor> flwTaskActors = new ArrayList<>();
                        if (NodeSetType.supervisor.eq(nodeModel.getSetType())) {
                            //根据创建人获取对应主管用户，放入执行人列表中
                            FlowCreator flowCreator = execution.getFlowCreator();
                            AuthUser authUser = authUserRepository.find(new UserId(Long.parseLong(flowCreator.getCreateId())));
                            if (authUser != null) {
                                // to do，获取部门id
                                Id deptId = authUser.getTenantId();
                                Department department = departmentRepository.find(deptId);
                                if (department != null && department.getHodId() != null) {
                                    AuthUser hod = authUserRepository.find(department.getHodId());
                                    flwTaskActors.add(FlwTaskActor.ofFlowCreator(new FlowCreator(hod.getUserId().getUserId().toString(), hod.getUserName().getUserName())));
                                }
                            }
                        }
                        //3-角色
                        else if (NodeSetType.role.eq(nodeModel.getSetType())) {
                            List<NodeAssignee> nodeAssigneeList = nodeModel.getNodeAssigneeList();
                            if (!nodeAssigneeList.isEmpty()) {
                                for (NodeAssignee nodeAssignee : nodeAssigneeList) {
                                    //根据role获取所有用户列表，放入执行人列表中
                                    String roleId = nodeAssignee.getId();
                                    List<AuthUserRole> users = authUserRoleRepository.find(new Id(Long.parseLong(roleId)));
                                    for (AuthUserRole authUserRole : users) {
                                        flwTaskActors.add(FlwTaskActor.ofFlowCreator(new FlowCreator(authUserRole.getUserId().getUserId().toString(), authUserRole.getUserName().getUserName())));
                                    }
                                }
                            }
                        }
                        if (!CollectionUtils.isEmpty(flwTaskActors)) {
                            return flwTaskActors;
                        }

                        return nodeModel.getNodeAssigneeList().stream().map(FlwTaskActor::ofNodeAssignee).collect(Collectors.toList());
                    }

                    @Override
                    public boolean isAllowed(NodeModel nodeModel, FlowCreator flowCreator) {
                        // 执行判断合法性
                        return true;
                    }
                }
        );
        FlowInstanceId instance = null;
        //获取审批流实例
        Optional<FlwInstance> flwInstance = Optional.empty();
        if (flowProcessUniqueId.getProcessId() != null) {
            flwInstance = flowLongEngine.startInstanceById(flowProcessUniqueId.getProcessId(), FlowCreator.of(flowUserInfo.getId(), flowUserInfo.getName()), flowBusinessKey.getBusinessKey());
        } else {
            ProcessService processService = flowLongEngine.processService();
            FlwProcess process = processService.getProcessByKey(null, flowProcessUniqueId.getProcessKey());
            if (process != null) {
                flwInstance = flowLongEngine.startInstanceById(process.getId(), FlowCreator.of(flowUserInfo.getId(), flowUserInfo.getName()), flowBusinessKey.getBusinessKey());
            }
        }
        if (flwInstance.isPresent()) {
            instance = new FlowInstanceId(flwInstance.get().getId());
        }
        return instance;
    }

    @Override
    public void executeFlowTask(FlowInstanceId flowInstanceId, FlowUserInfo flowUserInfo) {
        FlowCreator flowCreator = new FlowCreator(flowUserInfo.getId(), flowUserInfo.getName());
        QueryService queryService = flowLongEngine.queryService();
        List<FlwTask> flwTaskList = queryService.getTasksByInstanceId(flowInstanceId.getInstanceId());
        for (FlwTask flwTask : flwTaskList) {
            this.flowLongEngine.executeTask(flwTask.getId(), flowCreator);
        }
//        executeTask(flowInstance.getInstanceId(), flowCreator, flwTask -> this.flowLongEngine.executeTask(flwTask.getId(), flowCreator));
    }

    @Override
    public boolean instanceApproved(FlowInstanceId flowInstanceId) {
        boolean isApproved = false;
        QueryService queryService = flowLongEngine.queryService();
        FlwHisInstance histInstance = queryService.getHistInstance(flowInstanceId.getInstanceId());
        if(histInstance != null && histInstance.getInstanceState().equals(InstanceState.complete.getValue())){
            isApproved = true;
        }
        return isApproved;
    }
    @Override
    public List<FlowTask> getToDoTaskList(FlowUserInfo flowUserInfo) {
        List<FlowTask> toDoTaskList = approveFlowRepository.findTodoTasksByUserId(new UserId(Long.parseLong(flowUserInfo.getId())));
        return toDoTaskList;
    }
    @Override
    public List<FlowTask> getCcTaskList(FlowUserInfo flowUserInfo) {
        List<FlowTask> ccTaskList = approveFlowRepository.findCcTasksByUserId(new UserId(Long.parseLong(flowUserInfo.getId())));
        return ccTaskList;
    }
    @Override
    public List<FlowTask> getApprovedTaskList(FlowUserInfo flowUserInfo) {
        List<FlowTask> approvedTaskList = approveFlowRepository.findHisApprovedTasksByUserId(new UserId(Long.parseLong(flowUserInfo.getId())));
        return approvedTaskList;
    }
    @Override
    public List<FlowInstance> getApplyInstanceList(FlowUserInfo flowUserInfo) {
        List<FlowInstance> applyInstanceList = approveFlowRepository.findApplyInstancesByUserId(new UserId(Long.parseLong(flowUserInfo.getId())));
        return applyInstanceList;
    }
    private void executeTask(Long instanceId, FlowCreator flowCreator, Consumer<FlwTask> flwTaskConsumer) {
        QueryService queryService = flowLongEngine.queryService();
        List<FlwTask> flwTaskList = queryService.getTasksByInstanceId(instanceId);
        for (FlwTask flwTask : flwTaskList) {
            List<FlwTaskActor> taskActors = queryService.getTaskActorsByTaskId(flwTask.getId());
            if (null != taskActors && taskActors.stream()
                    // 找到当前对应审批的任务执行
                    .anyMatch(t -> Objects.equals(t.getActorId(), flowCreator.getCreateId()))) {
                flwTaskConsumer.accept(flwTask);
            }
        }
    }

}
