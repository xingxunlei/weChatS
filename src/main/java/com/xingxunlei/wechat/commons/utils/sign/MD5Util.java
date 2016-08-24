/**
 * SignUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： 加签验签工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-18 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils.sign;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;


/**
 * ClassName:SignUtil
 * Function: 加签验签工具类
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-18		下午4:42:25
 *
 * @see 	 
 */
public class MD5Util {
    
    public static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    
    /**
     * 取得文件的哈希值
     * 
     * @param fileName
     *            文件路径
     * @param hashType
     *            哈希类型
     * @return 文件哈希值
     * @throws Exception
     */
    public static byte[] getBytesFromFile(File f) {
        if (f == null)
            return null;
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(10000);
            byte[] b = new byte[10000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            System.out.println("e>>>>>>>>" + e.getMessage());
        }
        return null;
    }
    
    /**
     * 取得文件的哈希值
     * 
     * @param fileName
     *            文件路径
     * @param hashType
     *            哈希类型
     * @return 文件哈希值
     * @throws Exception
     */
    public static String getHash(String fileName, String hashType) throws Exception {
        InputStream fis;
        fis = new FileInputStream(fileName);// 读取文件
        byte[] buffer = new byte[1024];
        MessageDigest md5 = MessageDigest.getInstance(hashType);
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();
        return byteToStr(md5.digest());
    }
    
    /**
     * byteToStr:转换字节数组为16进制字符串
     *
     * @param  byteArray 字节数组
     * @return String    16进制字符串
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    
    /**
     * byteToHexStr:转换字节为16进制字符串
     *
     * @param  mByte  字节
     * @return String 16进制字符串
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = hexChar[(mByte >>> 4) & 0X0F];
        tempArr[1] = hexChar[mByte & 0X0F];
        
        String s = new String(tempArr);
        return s;
    }
    
}

