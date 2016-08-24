/**
 * XssUtilTest.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-24 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * ClassName:XssUtilTest
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-24		上午10:56:54
 *
 * @see 	 
 */
public class XssUtilTest {
    
    private static String s;

    @Before
    public void setUp() throws Exception {
        s = "sssssssssssss\\sdfjqw;lfjaslkdfjo;qi;wefjklasjdfljsa";
    }

    @Test
    public void testCleanXSS() {
        XssUtil.cleanXSS(s);
    }

}

