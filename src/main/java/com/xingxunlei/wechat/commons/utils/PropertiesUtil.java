/**
 * PropertiesUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： Properties属性文件工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-17 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.commons.utils;

import java.util.ResourceBundle;

/**
 * Properties属性文件工具类
 * 
 * @author Simon
 * @version Ver 1.1
 * @since Ver 1.1
 * @Date 2016-8-17 上午10:05:37
 * 
 */
public class PropertiesUtil {
    private static ResourceBundle rb;

    static {
        rb = ResourceBundle.getBundle("config");
    }

    /**
     * getValue:获取属性值
     * 
     * @param key 要取的属性值的key
     * @return String key对应的属性值
     * @since CodingExample　Ver 1.1
     */
    public static String getValue(String key) {
        return rb.getString(key);
    }

    /**
     * isOff:判断开关是否关闭状态
     * 
     * @param key 开关的key
     * @return boolean 开关是否关闭状态（true，关闭。false，非关闭）
     * @since CodingExample　Ver 1.1
     */
    public static boolean isOff(String key) {
        String str = getValue(key);
        if ("".equals(str) || str == null || str.length() == 0) {
            return false;
        } else if ("OFF".equals(str.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }
    
}
