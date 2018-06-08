/**
 * @(#)TimeUtil.java, 2018-06-08.
 * <p>
 * Copyright 2018 Stalary.
 */
package com.rookie.tools.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * TimeUtil
 *
 * @author lirongqian
 * @since 2018/06/08
 */
public class TimeUtil {

    /**
     * 增加指定天数
     * @param date 基础时间
     * @param days 增加天数
     * @return 操作后的日期
     */
    public static Date operateDays(Date date, int days) {
        return new Date(date.getTime() + 86400000L * days);
    }

    /**
     * 将LocalDateTime转化为Date
     * @param localDateTime
     * @return Date
     */
    public static Date localToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将Date转化为LocalDateTime
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocal(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}