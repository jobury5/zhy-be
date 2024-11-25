package com.zhy.domain.entity.common;

import lombok.Data;

import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/9/19 14:41
 */
@Data
public class ZhyPage<T> {

    private long pageNo;

    private long pageSize;

    private long totalPage;

    private long totalSize;

    private List<T> records;

}
