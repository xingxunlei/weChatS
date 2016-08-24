/**
 * EncryptUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： 加密工具类 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-23 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.commons.utils.sign;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

/**
 * ClassName:EncryptUtil 
 * Function: 加密工具类
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-23 下午5:33:34
 * 
 * @see
 */
public class Sha1Util {

    public static final int INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final String ALGORITHM = "SHA-1";

    /**
     * 对密码明文进行SHA-1加密 返回加密因子和密文
     * 
     * @param plainText
     *            密码明文
     * @return Map{SALT->saltVal,PASSWD->passwdVal}
     */
    public Map<String, String> encrypt(String plainText) {
        // 构造返回Map对象
        Map<String, String> passwdMap = new HashMap<String, String>();
        // 获取随机加密因子
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        passwdMap.put("SALT", Encodes.encodeHex(salt));
        // 用加密因子对明文进行SHA-1加密
        byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
        passwdMap.put("PASSWD", Encodes.encodeHex(hashPassword));
        return passwdMap;
    }

    /**
     * 指定加密因子对明文进行加密
     * 
     * @param plainText
     *            密码明文
     * @return 加密后的密文
     */
    public String encryptBySalt(String plainText, String salt) {
        // 将因子转换为字节数组类型
        byte[] old_salt = Encodes.decodeHex(salt);
        // 用加密因子对明文进行加密
        byte[] hashPassword = Digests.sha1(plainText.getBytes(), old_salt, INTERATIONS);
        // 返回加密后的密码
        return Encodes.encodeHex(hashPassword);
    }

}
