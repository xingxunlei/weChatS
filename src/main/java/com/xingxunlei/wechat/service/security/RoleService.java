/**
 * RoleService.java
 * com.xingxunlei.wechat.service.security
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.service.security;

import java.util.List;

/**
 * ClassName:RoleService
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:41:32
 *
 * @see 	 
 */
public interface RoleService {

    //根据用户查询用户权限信息
    public List<String> getHasPermissByUserID(Integer userId);

}

