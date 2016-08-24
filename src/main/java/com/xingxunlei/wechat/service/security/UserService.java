/**
 * UserService.java
 * com.xingxunlei.wechat.service.security
 *
 * Function： UserService
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.service.security;

import com.xingxunlei.wechat.model.cms.security.UserModel;

/**
 * ClassName:UserService
 * Function: UserService
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:41:51
 *
 * @see 	 
 */
public interface UserService {

    //根据用户名查询用户信息
    public UserModel getByAccount(String username);

    //更新用户登录时间
    public void updateLoginTime(Integer id);

}

