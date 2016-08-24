/**
 * UserDAO.java
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

import com.xingxunlei.wechat.model.cms.security.UserModel;
import java.util.List;

/**
 * ClassName:UserDAO
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:46:56
 *
 * @see 	 
 */
public interface UserDAO {

    public List<UserModel> findUserByAttr(UserModel user);

}

