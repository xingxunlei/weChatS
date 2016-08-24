/**
 * StreamUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： 流工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-19 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;

/**
 * ClassName:StreamUtil
 * Function: 流工具类
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-19		下午3:56:33
 *
 * @see 	 
 */
public class StreamUtil {
    private static Logger LOG = Logger.getLogger(StreamUtil.class);
    
    /**
     * stream2str:InputStream 转换为 String
     *
     * @param  InputStream 输入流
     * @return String    转换后的字符串
     * @throws 转换异常时返回 null
     * @since  CodingExample　Ver 1.1
     */
    public static String stream2str(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        try {
            while((i = is.read()) != -1){
                baos.write(i);
            }
        } catch (IOException e) {
            LOG.error(String.format("stream2str falied [%s]", e));
            return null;
        }
        return baos.toString();
    }
    
    /**
     * str2stream:string 转换为 stream
     *
     * @param  String s 待转换的字符串 
     * @return InputStream    转换后的流
     * @since  CodingExample　Ver 1.1
     */
    public static InputStream str2stream(String s) {
        InputStream is = new ByteArrayInputStream(s.getBytes());
        return is;
    }

}

