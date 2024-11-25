package com.zhy.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: jobury
 * @Date: 2024/9/21 21:28
 */
@Data
public class AttachUploadDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long attachId;

}
