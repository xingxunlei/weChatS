/**
 * RoleDAO.java
 * com.xingxunlei.wechat.dao.security
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.dao.security;

import java.util.List;

/**
 * ClassName:RoleDAO
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:46:22
 *
 * @see 	 
 */
public interface RoleDAO {

    List<String> getHasPermissByUserID(Integer userId);

}

