package com.zhy.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/19 15:48
 */
@Data
public class ZhyPageDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private long pageNo;

    private long pageSize;

    private long totalPage;

    private long totalSize;

    private List<T> list;

}
