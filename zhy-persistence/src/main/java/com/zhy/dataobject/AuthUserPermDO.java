package com.zhy.dataobject;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: jobury
 * @Date: 2024/10/9 14:53
 */

@Getter
@Setter
public class AuthUserPermDO {

    private Long userId;

    private String permName;

    private String urlPerm;

    private String btnPerm;


}
