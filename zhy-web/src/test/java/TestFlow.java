import com.zhy.ZhyWebApplication;
import com.zhy.application.ApproveFlowService;
import com.zhy.application.CrewCertService;
import com.zhy.domain.entity.AuthUser;
import com.zhy.dto.CrewCertReqDTO;
import com.zhy.types.*;
import com.zhy.types.approveflow.FlowBusinessKey;
import com.zhy.types.approveflow.FlowInstanceId;
import com.zhy.types.approveflow.FlowProcessUniqueId;
import com.zhy.types.approveflow.FlowUserInfo;
import com.zhy.types.approveflow.FlowProcessEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: jobury
 * @Date: 2024/8/20 14:43
 */

@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class TestFlow {

    @Autowired
    private ApproveFlowService approveFlowService;

    @Autowired
    private CrewCertService crewCertService;

    @Test
    public void testApproveFlowForSupervisor(){
        FlowProcessUniqueId flowProcessUniqueId = new FlowProcessUniqueId(FlowProcessEnum.CERT_APPLY);
        FlowUserInfo flowUserInfo = new FlowUserInfo("10000000", "jobury");
        FlowBusinessKey flowBusinessKey = new FlowBusinessKey("busi001");
        FlowInstanceId flowInstanceId = approveFlowService.startFlow(flowProcessUniqueId, flowUserInfo, flowBusinessKey);



        if(flowInstanceId != null) {
            FlowUserInfo supervisor = new FlowUserInfo("10000001", "sunxue");
            approveFlowService.executeFlowTask(flowInstanceId, supervisor);
        }
        //assertNotNull(authUserDTO);
        log.info("completed");
    }

    @Test
    public void testApproveFlowForRole(){
        FlowProcessUniqueId flowProcessUniqueId = new FlowProcessUniqueId(FlowProcessEnum.REBURSEMENT);
        FlowUserInfo flowUserInfo = new FlowUserInfo("10000000", "jobury");
        FlowBusinessKey flowBusinessKey = new FlowBusinessKey("busi001");
        FlowInstanceId flowInstanceId = approveFlowService.startFlow(flowProcessUniqueId, flowUserInfo, flowBusinessKey);
        if(flowInstanceId != null) {
            FlowUserInfo supervisor = new FlowUserInfo("10000002", "sunxue2");
            approveFlowService.executeFlowTask(flowInstanceId, supervisor);
        }
        //assertNotNull(authUserDTO);
        log.info("completed");
    }

    @Test
    public void testApproveFlowForBusiness(){
        Id crewId = new Id(10033362L);
        Id certId = new Id(10066L);
        CertNumber certNumber = new CertNumber("PDA202402249");
        Authority authority = new Authority("上海海事局");
        Id attachId = new Id(324402L);
        AuthUser authUser = new AuthUser();
        authUser.setUserId(new UserId(10000000L));
        authUser.setUserName(new UserName("jobury"));
        CrewCertReqDTO crewCertReqDTO = crewCertService.applyCert(crewId, certId, certNumber, authority, attachId, authUser);
        assertNotNull(crewCertReqDTO);

        Id newReqId = new Id(crewCertReqDTO.getReqId());
        FlowInstanceId flowInstanceId = new FlowInstanceId(crewCertReqDTO.getInstanceId());
        AuthUser approveUser = new AuthUser();
        approveUser.setUserId(new UserId(10000001L));
        approveUser.setUserName(new UserName("sunxun"));
        Boolean completed = crewCertService.approveCert(newReqId, flowInstanceId, approveUser);
        assertTrue(completed);
    }

}
