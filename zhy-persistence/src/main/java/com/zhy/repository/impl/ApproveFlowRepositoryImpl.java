package com.zhy.repository.impl;

import cn.hutool.core.date.DateUtil;
import com.aizuda.bpm.engine.core.enums.ActorType;
import com.aizuda.bpm.engine.core.enums.TaskType;
import com.aizuda.bpm.engine.entity.*;
import com.aizuda.bpm.mybatisplus.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhy.domain.entity.approveflow.FlowInstance;
import com.zhy.domain.entity.approveflow.FlowTask;
import com.zhy.repository.ApproveFlowRepository;
import com.zhy.types.Id;
import com.zhy.types.Name;
import com.zhy.types.UserId;
import com.zhy.types.UserName;
import com.zhy.types.approveflow.FlowBusinessKey;
import com.zhy.types.approveflow.FlowProcessEnum;
import com.zhy.types.approveflow.FlowTaskKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/9 16:11
 */

@Repository
public class ApproveFlowRepositoryImpl implements ApproveFlowRepository {
    @Autowired
    private FlwInstanceMapper flwInstanceMapper;
    @Autowired
    private FlwTaskMapper flwTaskMapper;
    @Autowired
    private FlwTaskActorMapper flwTaskActorMapper;
    @Autowired
    private FlwHisInstanceMapper flwHisInstanceMapper;
    @Autowired
    private FlwHisTaskMapper flwHisTaskMapper;
    @Autowired
    private FlwHisTaskActorMapper flwHisTaskActorMapper;

    @Override
    public List<FlowTask> findTodoTasksByUserId(UserId userId) {
        //待审批任务
        List<FlowTask> flowTasks = new ArrayList<>();
        LambdaQueryWrapper<FlwTaskActor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlwTaskActor::getActorId, userId.getUserId());
        queryWrapper.eq(FlwTaskActor::getActorType, ActorType.user.getValue());
        List<FlwTaskActor> flwTaskActors = flwTaskActorMapper.selectList(queryWrapper);
        for (FlwTaskActor flwTaskActor : flwTaskActors) {
            FlwTask flwTask = flwTaskMapper.selectById(flwTaskActor.getTaskId());
            FlowTask flowTask = new FlowTask();
            flowTask.setTaskId(new Id(flwTask.getId()));
            flowTask.setTaskName(new Name(flwTask.getTaskName()));
            flowTask.setInstanceId(new Id(flwTask.getInstanceId()));
            FlwInstance flwInstance = flwInstanceMapper.selectById(flwTask.getInstanceId());
            if (flwInstance != null) {
                flowTask.setBusinessKey(new FlowBusinessKey(flwInstance.getBusinessKey()));
                flowTask.setFlowProcess(FlowProcessEnum.of(flwInstance.getProcessId()));
            }
            flowTasks.add(flowTask);
        }
        return flowTasks;
    }

    @Override
    public List<FlowTask> findCcTasksByUserId(UserId userId) {
        //抄送我的任务
        List<FlowTask> flowTasks = new ArrayList<>();
        LambdaQueryWrapper<FlwHisTaskActor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlwHisTaskActor::getActorId, userId.getUserId());
        queryWrapper.eq(FlwHisTaskActor::getActorType, ActorType.user.getValue());
        List<FlwHisTaskActor> flwHisTaskActors = flwHisTaskActorMapper.selectList(queryWrapper);
        for (FlwHisTaskActor flwHisTaskActor : flwHisTaskActors) {
            FlwHisTask flwHisTask = flwHisTaskMapper.selectById(flwHisTaskActor.getTaskId());
            //抄送任务
            if (flwHisTask.getTaskType().equals(TaskType.cc.getValue())) {
                FlowTask flowTask = new FlowTask();
                flowTask.setTaskId(new Id(flwHisTask.getId()));
                flowTask.setTaskName(new Name(flwHisTask.getTaskName()));
                flowTask.setInstanceId(new Id(flwHisTask.getInstanceId()));
                FlwHisInstance flwHisInstance = flwHisInstanceMapper.selectById(flwHisTask.getInstanceId());
                if (flwHisInstance != null) {
                    flowTask.setBusinessKey(new FlowBusinessKey(flwHisInstance.getBusinessKey()));
                    flowTask.setFlowProcess(FlowProcessEnum.of(flwHisInstance.getProcessId()));
                }
                flowTasks.add(flowTask);
            }
        }
        return flowTasks;
    }

    @Override
    public List<FlowTask> findHisApprovedTasksByUserId(UserId userId) {
        //我已审批的任务
        List<FlowTask> flowTasks = new ArrayList<>();
        LambdaQueryWrapper<FlwHisTaskActor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlwHisTaskActor::getActorId, userId.getUserId());
        queryWrapper.in(FlwHisTaskActor::getActorType, ActorType.user.getValue(), ActorType.role.getValue());
        List<FlwHisTaskActor> flwHisTaskActors = flwHisTaskActorMapper.selectList(queryWrapper);
        for (FlwHisTaskActor flwHisTaskActor : flwHisTaskActors) {
            FlwHisTask flwHisTask = flwHisTaskMapper.selectById(flwHisTaskActor.getTaskId());
            FlowTask flowTask = new FlowTask();
            flowTask.setTaskId(new Id(flwHisTask.getId()));
            flowTask.setTaskName(new Name(flwHisTask.getTaskName()));
            flowTask.setInstanceId(new Id(flwHisTask.getInstanceId()));
            FlwHisInstance flwHisInstance = flwHisInstanceMapper.selectById(flwHisTask.getInstanceId());
            if (flwHisInstance != null) {
                flowTask.setBusinessKey(new FlowBusinessKey(flwHisInstance.getBusinessKey()));
                flowTask.setFlowProcess(FlowProcessEnum.of(flwHisInstance.getProcessId()));
            }
            flowTasks.add(flowTask);
        }
        return flowTasks;
    }

    @Override
    public List<FlowInstance> findApplyInstancesByUserId(UserId userId) {
        //我的申请
        LambdaQueryWrapper<FlwHisInstance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FlwHisInstance::getCreateId, userId.getUserId());
        List<FlwHisInstance> flwHisInstances = flwHisInstanceMapper.selectList(queryWrapper);
        List<FlowInstance> flowInstances = new ArrayList<>();
        for (FlwHisInstance flwHisInstance : flwHisInstances) {
            FlowInstance flowInstance = new FlowInstance();
            flowInstance.setFlowProcess(FlowProcessEnum.of(flwHisInstance.getProcessId()));
            flowInstance.setInstanceId(new Id(flwHisInstance.getId()));
            flowInstance.setBusinessKey(new FlowBusinessKey(flwHisInstance.getBusinessKey()));
            flowInstance.setCreateUserId(new UserId(Long.parseLong(flwHisInstance.getCreateId())));
            flowInstance.setDuration(flwHisInstance.getDuration());
            flowInstance.setCreateUserName(new UserName(flwHisInstance.getCreateBy()));
            flowInstance.setCurrentTaskKey(new FlowTaskKey(flwHisInstance.getCurrentNodeKey()));
            flowInstance.setCurrentTaskName(new Name(flwHisInstance.getCurrentNodeName()));
            flowInstance.setStartTime(DateUtil.toLocalDateTime(flwHisInstance.getCreateTime()));
            flowInstance.setEndTime(flwHisInstance.getEndTime() != null ? DateUtil.toLocalDateTime(flwHisInstance.getEndTime()) : null);
            flowInstances.add(flowInstance);
        }
        return flowInstances;
    }


}
