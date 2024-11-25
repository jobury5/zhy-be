import cn.hutool.core.collection.CollectionUtil;
import com.zhy.ZhyWebApplication;
import com.zhy.domain.entity.ocr.*;
import com.zhy.external.OcrService;
import com.zhy.external.remote.AliyunOcrService;
import com.zhy.types.aliyun.OcrTemplate;
import com.zhy.types.aliyun.OssKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/13 13:21
 */

@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class TestOcr {

    @Autowired
    private OcrService ocrService;

    @Autowired
    private AliyunOcrService aliyunOcrService;

    @Test
    public void testIdentity(){
        OssKey ossKey = new OssKey("profile/identity/2024/09/20240911AaKbqGdR.pdf");
        //OssKey ossKey = new OssKey("profile/identity/2024/09/20231102ktus8fre.jpg");
        OcrIdentity identity = ocrService.recognizeIdentity(ossKey);
        assert identity!=null;
        System.out.println(identity);
    }

    @Test
    public void testPassport(){
        OssKey ossKey = new OssKey("profile/passport/2024/09/20240903GOwjVwFP.pdf");
        //OssKey ossKey = new OssKey("profile/passport/2024/09/202409029eOaey09.jpg");
        OcrPassport passport = ocrService.recognizePassport(ossKey);
        assert passport!=null;
        System.out.println(passport);
    }

    @Test
    public void testInvoice(){
        OssKey ossKey = new OssKey("finance/reim/2024/08/20240802f76jpzd9.pdf");
        List<OcrInvoice> invoiceList = ocrService.recognizeInvoices(ossKey);
        assert CollectionUtil.isNotEmpty(invoiceList);
        for(OcrInvoice invoice : invoiceList){
            System.out.println(invoice);
        }
    }

    @Test
    public void testCert(){
        OssKey ossKey = new OssKey("work/cert/2024/09/20240912KWHLoW7u.pdf");
        OcrCert ocrCert = ocrService.recognizeCert(OcrTemplate.SMPP, ossKey);
        assert ocrCert != null;
        System.out.println(ocrCert);
    }

    @Test
    public void testCertTable(){
        OssKey ossKey = new OssKey("work/cert/2024/09/20240913Cctdv7iI.pdf");
        OcrCert ocrCert = ocrService.recognizeCert(OcrTemplate.STCW, ossKey);
        assert ocrCert != null;
        System.out.println(ocrCert);
    }

    @Test
    public void testCertTablePage2ForSTCW(){
        OssKey ossKey = new OssKey("work/cert/2023/11/2023112152a4wz3l.pdf");
        OcrCert ocrCert = ocrService.recognizeCert(OcrTemplate.STCW, ossKey);
        assert ocrCert != null;
        System.out.println(ocrCert);
        for(OcrCertItem item : ocrCert.getOcrCertItems()){
            System.out.println(item);
        }
    }

    @Test
    public void testCertTablePageCOC(){
        OssKey ossKey = new OssKey("work/cert/2023/11/2023111429zv3phn.pdf");
        OcrCert ocrCert = ocrService.recognizeCert(OcrTemplate.COC, ossKey);
        assert ocrCert != null;
        System.out.println(ocrCert);
    }


}
