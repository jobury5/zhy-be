import com.zhy.ZhyWebApplication;
import com.zhy.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: jobury
 * @Date: 2024/9/30 17:24
 */

@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class TestRedis {

    @Autowired
    private RedisService redisService;

    @Test
    public void testSetGet(){
        redisService.set("testKey", "redis");
        Boolean hasKey = redisService.hasKey("testKey");
        assertTrue(hasKey);
        String value = (String)redisService.get("testKey");
        assertTrue(value.equals("redis"));
    }

    @Test
    public void testHashKey(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user1", "token1");
        map.put("user2", "token2");
        redisService.hmset("userToken", map,1L, TimeUnit.MINUTES);
        Map<Object, Object> userToken = redisService.hmget("userToken");
        assertTrue(userToken.containsKey("user1"));
        assertTrue(userToken.containsKey("user2"));
        Boolean hset = redisService.hset("userToken", "user3", "token3",1L, TimeUnit.MINUTES);
        assertTrue(hset);
        String token = (String)redisService.hget("userToken", "user3");
        assertTrue(token.equals("token3"));
    }

    @Test
    public void testGetHashKey(){
        Map urlPermRolesRule = redisService.hmget("URL_PERM_ROLES_RULE");
        System.out.println(urlPermRolesRule);

        for(Object key: urlPermRolesRule.keySet()){
            String urlPerm = (String)key;
            System.out.println("url========" + urlPerm);
            List<String> values = (List)urlPermRolesRule.get(key);
            for(String value : values){
                System.out.println("role===" + value);
            }
        }

    }


}
