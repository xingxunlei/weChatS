/**
 * DateTimeUtil.java
 * com.xingxunlei.wechat.commons.utils.date
 *
 * Function： 日期时间工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-19 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils.date;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:DateTimeUtil
 * Function: 日期时间工具类
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-19		下午2:17:09
 *
 * @see 	 
 */
public class DateTimeUtil {
    private static Logger LOG = Logger.getLogger(DateTimeUtil.class);
    
    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";
    public static final String DEFAULT_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * getDateTime:字符串转日期
     *
     * @param  dateString 待转换的字符串
     * @param  format 待转换的日期格式
     * @return Date   转换后的日期对象
     * @throws 转换异常返回 null
     * @since  CodingExample　Ver 1.1
     */
    public static Date getDateTime(String dateString, String format){
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sf.parse(dateString);
        } catch (ParseException e) {
            LOG.error(String.format("[%s] toDate [%s] falied: [%s]", dateString, format, e));
        }
        return date;
    }
    
    /**
     * 
     * getDateTime:字符串转日期
     *
     * @param  dateTimeString 待转换的字符串
     * @return Date    转换后的日期对象(yyyy-MM-dd HH:mm:ss)
     * @throws 转换异常返回 null
     * @since  CodingExample　Ver 1.1
     */
    public static Date getDateTime(String dateTimeString){
        return getDateTime(dateTimeString,DEFAULT_FORMAT_DATETIME);
    }
    
    /**
     * getDate:字符串转日期
     *
     * @param  date 待转换的字符串
     * @return Date 转换后的日期对象(yyyy-MM-dd)
     * @throws 转换异常返回 null
     * @since  CodingExample　Ver 1.1
     */
    public static Date getDate(String date){
        return getDateTime(date, DEFAULT_FORMAT_DATE);
    }
    
    /**
     * getTime:字符串转日期
     *
     * @param  time 待转换的字符串
     * @return Date 转换后的日期对象(HH:mm:ss)
     * @throws 转换异常返回 null
     * @since  CodingExample　Ver 1.1
     */
    public static Date getTime(String time){
        return getDateTime(time,DEFAULT_FORMAT_TIME);
    }
    
    
    /**
     * toDateString:日期转换为日期字符串
     *
     * @param date   待转换的日期对象
     * @param format 转换格式
     * @return String  转换后的日期字符串
     * @since  CodingExample　Ver 1.1
     */
    public static String toDateString(Date date,String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }
    
    /**
     * toDateTimeStr:日期转换为日期字符串
     *
     * @param date   待转换的日期对象
     * @return String  转换后的日期字符串(yyyy-MM-dd HH:mm:ss)
     * @since  CodingExample　Ver 1.1
     */
    public static String toDateTimeStr(Date date){
        return toDateString(date, DEFAULT_FORMAT_DATETIME);
    }
    
    /**
     * toDateTimeStr:日期转换为日期字符串
     *
     * @param date   待转换的日期对象
     * @return String  转换后的日期字符串(yyyy-MM-dd)
     * @since  CodingExample　Ver 1.1
     */
    public static String toDateStr(Date date){
        return toDateString(date, DEFAULT_FORMAT_DATE);
    }
    
    /**
     * toDateTimeStr:日期转换为日期字符串
     *
     * @param date   待转换的日期对象
     * @return String  转换后的日期字符串(HH:mm:ss)
     * @since  CodingExample　Ver 1.1
     */
    public static String toTimeStr(Date date){
        return toDateString(date, DEFAULT_FORMAT_TIME);
    }
    
    /**
     * getToday:获取指定时间的当前日期
     *
     * @return Date   当前日期对象(yyyy-MM-dd HH:mm:ss)
     * @since  CodingExample　Ver 1.1
     */
    public static Date getToday(String time){
        String today = currentDateStr();
        return getDateTime(today + " " + time,DEFAULT_FORMAT_DATETIME);
    }
    
    /**
     * currentTimeStr:获取当前时间字符串
     *
     * @return String  当前时间字符串(HH:mm:ss)
     * @since  CodingExample　Ver 1.1
     */
    public static String currentTimeStr(){
        return toDateString(new Date(), DEFAULT_FORMAT_TIME);
    }
    
    /**
     * currentTimeStr:获取当期日期字符串
     *
     * @return String  当前时间字符串(yyyy-MM-dd)
     * @since  CodingExample　Ver 1.1
     */
    public static String currentDateStr(){
        return toDateString(new Date(), DEFAULT_FORMAT_DATE);
    }
    
    /**
     * currentStr:获取当前日期时间字符串
     *
     * @return String  当前时间字符串(yyyy-MM-dd HH:mm:ss)
     * @since  CodingExample　Ver 1.1
     */
    public static String currentStr(){
        return toDateString(new Date(), DEFAULT_FORMAT_DATETIME);
    }
    
    /**
     * current:获取当前日期时间
     *
     * @return Date  当前日期对象(yyyy-MM-dd HH:mm:ss)
     * @since  CodingExample　Ver 1.1
     */
    public static Date current(){
        return new Date();
    }
    
    /**
     * 格式化字符串，使用指定Date替换其中的时间参数,时间段使用{yyyy-mm-dd}标识
     * @param text
     * @param date
     * @return
     */
    public static String format(String text,Date date){
        int start = text.indexOf("{");
        int end = text.indexOf("}");
        while(start > 0 && end > 0){
            String subStr = text.substring(start, end+1);
            String format = text.substring(start+1, end);
            String dateStr = toDateString(date, format);
            text = text.replace(subStr, dateStr);
            
            start = text.indexOf("{");
            end = text.indexOf("}");
        }
        return text;
    }
    
    /**
     * isDate:是否日期类型字符串
     *
     * @param  dateString 待判断的字符串
     * @return boolean    是否日期类型
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static boolean isDate(String dateString){
        Date date = getDateTime(dateString);
        return (date == null? false:true);
    }

}

