import com.zhy.ZhyWebApplication;
import com.zhy.external.OssService;
import com.zhy.types.Url;
import com.zhy.types.aliyun.OssKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: jobury
 * @Date: 2024/9/10 19:10
 */

@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class TestOss {

    @Autowired
    private OssService ossService;

    @Test
    public void testGetSignedUrl(){
        Url url = ossService.getSignedUrl(new OssKey("work/cert/2024/09/202409020TGPMVMY.pdf"));
        System.out.println("url==========" + url.getUrl());
    }

    @Test
    public void testGetImgUrl(){
        Url url = ossService.getSignedImgUrlWithResize(new OssKey("profile/identity/2024/09/20240905aSOII6pE.png"), 10, 10);
        System.out.println("url==========" + url.getUrl());
    }

    @Test
    public void testUpload() throws Exception{
//        InputStream inputStream = new FileInputStream("/Users/qiaobusi/Downloads/20240905aSOII6pE.png");
//        ossService.upload(new OssKey("test/test.png"), inputStream);
    }



}
