/**
 * Copyright (c) 2015 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */
package com.lay.shop.pacs.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public final class DateUtils {
    
    private DateUtils() {}
    
    /**
     * 获取指定日期的最开始，即0时0分0秒0毫秒
     * @author 李光辉
     * @param date
     * @return
     * @since
     */
    public static Date getStartOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        return getStartOfDate(c);
    }
    
    /**
     * 获取指定日期的最开始，即0时0分0秒0毫秒
     * @author 李光辉
     * @param date
     * @return
     * @since
     */
    public static Date getStartOfDate(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        
        return date.getTime();
    }
    
    /**
     * 获取指定日期的最开始，即23时59分59秒999毫秒
     * @author 李光辉
     * @param date
     * @return
     * @since
     */
    public static Date getEndOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        return getEndOfDate(c);
    }
    
    /**
     * 获取指定日期的最开始，即23时59分59秒999毫秒
     * @author 李光辉
     * @param date
     * @return
     * @since
     */
    public static Date getEndOfDate(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND, 999);
        
        return date.getTime();
    }

    /**
     * 获取指定日期当天的时间段
     * @author 李光辉
     * @return
     * @since
     */
    public static Interval getDateInterval(Date date) {
        DateTime dt = LocalDate.fromDateFields(date).toDateTimeAtStartOfDay();
        return new Interval(dt, dt.plusDays(1));
    }

    /**
     * 获取指定日期当周的时间段
     * @author 李光辉
     * @return
     * @since
     */
    public static Interval getDateWeekInterval(Date date) {
        DateTime dt = LocalDate.fromDateFields(date).toDateTimeAtStartOfDay();
        DateTime start = dt.withDayOfWeek(DateTimeConstants.MONDAY);
        DateTime end = dt.withDayOfWeek(DateTimeConstants.SUNDAY).plusDays(1);
        return new Interval(start, end);
    }

    /**
     * 获取指定日期当月的时间段
     * @author 李光辉
     * @return
     * @since
     */
    public static Interval getDateMonthInterval(Date date) {
        DateTime dt = LocalDate.fromDateFields(date).toDateTimeAtStartOfDay();
        DateTime start = dt.withDayOfMonth(1);
        DateTime end = dt.plusMonths(1).withDayOfMonth(1);
        return new Interval(start, end);
    }

    /**
     * 获取指定日期月数内的时间段
     * @author 李光辉
     * @return
     * @since
     */
    public static Interval getMonthsInterval(Date date, int months) {
        DateTime dt = LocalDate.fromDateFields(date).toDateTimeAtStartOfDay();
        DateTime start = dt.minusMonths(months);
        DateTime end = dt.plusDays(1);
        return new Interval(start, end);
    }

}
