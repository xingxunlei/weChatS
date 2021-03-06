/**
 * RegexUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： 常用的正则表达式工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-11 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 常用的正则表达式工具类
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-11		下午1:44:53
 *
 * @see 	 
 */
public class RegexUtil {
    private static Logger LOG = Logger.getLogger(RegexUtil.class);
    
    /**
     * 判断是否手机号码
     *
     * @param  mobiles 入参
     * @return boolean 是否正确的手机号码
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static boolean isMobileNo(String mobiles) {
        if(StringUtils.isEmpty(mobiles)){
            return false;
        }
        try {
            Pattern p = Pattern.compile("^(13|14|15|17|18)[0-9]{9}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } catch (Exception e) {
            LOG.error("手机号码验证失败："+e);
            return false;
        }
        
    }
    
    /**
     * 判断是否电话号码
     *
     * @param  telephone 入参
     * @return boolean 是否正确的电话号码
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static boolean isTelephone(String telephone) {
        if(StringUtils.isEmpty(telephone)){
            return false;
        }
        try {
            Pattern p = Pattern.compile("^(\\d{3,4}-)?\\d{7,8}(\\-?[0-9]{1,4})?$");
            Matcher m = p.matcher(telephone);
            return m.matches();
        } catch (Exception e) {
            LOG.error("电话号码验证失败："+e);
            return false;
        }
        
    }
    
    /**
     * 判断是否邮箱地址
     *
     * @param  email 入参
     * @return boolean 是否正确的邮箱地址
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static boolean isEmail(String email) {
        if(StringUtils.isEmpty(email)){
            return false;
        }
        try {
            Pattern p = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
            Matcher m = p.matcher(email);
            return m.matches();
        } catch (Exception e) {
            LOG.error("邮箱地址验证失败："+e);
            return false;
        }
        
    }
    
    /**
     * 判断是否IP地址
     *
     * @param  ip 入参
     * @return boolean 是否正确的IP地址
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static boolean isIp(String ip) {
        if(StringUtils.isEmpty(ip)){
            return false;
        }
        try {
            Pattern p = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
            Matcher m = p.matcher(ip);
            return m.matches();
        } catch (Exception e) {
            LOG.error("IP地址验证失败："+e);
            return false;
        }
        
    }
    
    /**
     * 判断是否身份证号码
     *
     * @param  no 入参
     * @return boolean 是否正确的身份证号码
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static boolean isIDNo(String no) {
        if(StringUtils.isEmpty(no)){
            return false;
        }
        try {
            Pattern p1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
            Pattern p2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
            Matcher m1 = p1.matcher(no);
            Matcher m2 = p2.matcher(no);
            return m1.matches() | m2.matches();
        } catch (Exception e) {
            LOG.error("身份证号码验证失败："+e);
            return false;
        }
    }
    
    /**
     * 自定义正则表达式，匹配传入参数
     *
     * @param  str     待匹配的字符串
     * @param  pattern 自定义的正则表达式
     * @return boolean true/false
     * @since  CodingExample　Ver 1.1
     */
    public static boolean matcher(String str, String pattern) {
        if(StringUtils.isEmpty(str) || StringUtils.isEmpty(pattern)){
            return false;
        }
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.matches();
        } catch (Exception e) {
            LOG.error("验证失败："+e);
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(isMobileNo("18353852910"));
        System.out.println(isTelephone("021-0000000-0110"));
        System.out.println(isEmail("a@a.xx"));
        System.out.println(isIp("127.400.600.2"));
        System.out.println(isIDNo("11111111111111111"));
        System.out.println(matcher("a@a.xx", "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"));
    }

}

