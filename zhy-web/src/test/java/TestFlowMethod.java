import com.zhy.ZhyWebApplication;
import com.zhy.application.ApproveFlowService;
import com.zhy.domain.entity.approveflow.FlowInstance;
import com.zhy.domain.entity.approveflow.FlowTask;
import com.zhy.types.approveflow.FlowUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/10 13:45
 */

@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class TestFlowMethod {

    @Autowired
    private ApproveFlowService approveFlowService;

    @Test
    public void testToDoTasks(){
        List<FlowTask> toDoTaskList = approveFlowService.getToDoTaskList(new FlowUserInfo("10000001", "sunxue"));
        for(FlowTask flowTask : toDoTaskList){
            System.out.println(flowTask);
        }
    }

    @Test
    public void testCcTasks(){
        List<FlowTask> ccTaskList = approveFlowService.getCcTaskList(new FlowUserInfo("10000001", "sunxue"));
        for(FlowTask flowTask : ccTaskList){
            System.out.println(flowTask);
        }
    }

    @Test
    public void testApprovedTasks(){
        List<FlowTask> approvedTaskList = approveFlowService.getApprovedTaskList(new FlowUserInfo("10000001", "sunxue"));
        for(FlowTask flowTask : approvedTaskList){
            System.out.println(flowTask);
        }
    }

    @Test
    public void testApplyTasks(){
        List<FlowInstance> flowInstanceList = approveFlowService.getApplyInstanceList(new FlowUserInfo("10000000", "jobury"));
        for(FlowInstance flowInstance : flowInstanceList){
            System.out.println(flowInstance);
        }
    }

}
