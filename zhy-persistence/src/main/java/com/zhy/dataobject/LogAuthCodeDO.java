package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhy.dataobject.BaseDO;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobury
 * @since 2024-09-25
 */
@Getter
@Setter
@TableName("log_auth_code")
public class LogAuthCodeDO extends BaseDO {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Auth code
     */
    private String code;

    /**
     * Mobile area number
     */
    private Integer areaNumber;

    /**
     * Mobile number
     */
    private String mobile;

    /**
     * Email
     */
    private String email;

    /**
     * Code expiration time
     */
    private LocalDateTime expireAt;
}
