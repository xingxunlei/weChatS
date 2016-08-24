/**
 * VdateCodeController.java
 * com.xingxunlei.wechat.controller.security
 *
 * Function： 验证码Controller
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-23 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.controller.security;

import com.xingxunlei.wechat.commons.utils.VCodeUtil;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:VdateCodeController
 * Function: 验证码Controller
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-23		下午2:51:56
 *
 * @see 	 
 */
@Controller
@RequestMapping(value = "/common")
public class VdateCodeController {
    
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        //获取验证码
        String verifyCode = VCodeUtil.generateTextCode(VCodeUtil.TYPE_ALL_MIXED, 4, "0Ooil");
        request.getSession().setAttribute("validatecode", verifyCode.toLowerCase());
        response.setContentType("image/jpeg");
        //生成验证码图片
        BufferedImage bim = VCodeUtil.generateImageCode(verifyCode, 115, 42, 5, true, Color.WHITE, Color.BLACK, null);
        //返回到前台
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }

}

