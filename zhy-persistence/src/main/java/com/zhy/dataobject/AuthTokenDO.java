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
 * @since 2024-09-24
 */
@Getter
@Setter
@TableName("auth_token")
public class AuthTokenDO extends BaseDO {

    /**
     * Auth user id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Auth user id
     */
    private Long userId;

    /**
     * Access token
     */
    private String token;

    /**
     * Token expired time
     */
    private LocalDateTime expireAt;

    /**
     * Refresh token
     */
    private String refreshToken;

    /**
     * Refresh token expired time
     */
    private LocalDateTime refreshExpireAt;
}
