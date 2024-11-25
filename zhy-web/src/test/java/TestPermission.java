import com.zhy.ZhyWebApplication;
import com.zhy.application.PermissionService;
import com.zhy.dto.UrlPermRolesDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/8 18:14
 */
@Slf4j
@SpringBootTest(classes = ZhyWebApplication.class)
public class TestPermission {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void testPermission(){
        List<UrlPermRolesDTO> urlPermRolesList = permissionService.getUrlPermRolesList();
        for(UrlPermRolesDTO urlPermRolesDTO: urlPermRolesList){
            System.out.println(urlPermRolesDTO.getUrlPerm());
            System.out.println(urlPermRolesDTO.getRoles());
        }

    }


}
