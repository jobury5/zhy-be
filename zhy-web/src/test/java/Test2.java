import com.zhy.ZhyWebApplication;
import com.zhy.application.LoginService;
import com.zhy.dto.AccessTokenDTO;
import com.zhy.types.Password;
import com.zhy.types.UserName;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author: jobury
 * @Date: 2024/8/20 11:17
 */
@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class Test2 {

    @Autowired
    private LoginService loginService;

    @Test
    public void testMethod(){
        AccessTokenDTO accessTokenDTO = loginService.login(new UserName("jobury"), new Password("111"));
        assertNotNull(accessTokenDTO);
        log.info("completed");
    }

}
