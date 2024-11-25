import com.zhy.ZhyWebApplication;
import com.zhy.external.MailService;
import com.zhy.types.MailAddress;
import com.zhy.types.MailContent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jobury
 * @Date: 2024/9/11 19:01
 */

@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class TestMail {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendEmail(){
        String content = "Hello, my name is {name}. I am {age} years old.";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jobury");
        map.put("age", 10);
        mailService.sendMail(new MailAddress("qiaowei@aisailings.com"), new MailContent("test", content), map);
    }



}
