package com.zhy.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ZhyDateUtil {

    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.ofInstant(java.time.Instant.now(), ZoneId.systemDefault());
    }

    public static LocalDate toLocalDate(String dateString) {
        try {
            // 尝试使用不同的日期格式解析日期
            dateString = StrUtil.removeAll(dateString, ' ', '.','-',',');
            dateString = dateString.toUpperCase();
            DateTime dateTime = DateUtil.parse(dateString, "ddMMMyyyy", "yyyy年MM月dd日", "dMMMyyyy", "MMMddyyyy");
            return dateTime.toLocalDateTime().toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }






}
