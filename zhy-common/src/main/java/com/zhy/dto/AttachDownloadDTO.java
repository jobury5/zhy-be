package com.zhy.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: jobury
 * @Date: 2024/9/23 15:18
 */
@Data
public class AttachDownloadDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long attachId;

    private String url;
}
