/**
 * UserDAOImpl.java
 * com.xingxunlei.wechat.dao.impl.security
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.dao.impl.security;

import com.xingxunlei.wechat.model.cms.security.UserModel;
import java.util.List;

import com.xingxunlei.wechat.dao.impl.BaseDAO;
import com.xingxunlei.wechat.dao.security.UserDAO;

/**
 * ClassName:UserDAOImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:37:54
 *
 * @see 	 
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    
    private static final String FIND_USER_BY_ATTR = "com.xingxunlei.wechat.security.user.findUserByAttr";

    public List<UserModel> findUserByAttr(UserModel user) {
        return sqlSession.selectList(FIND_USER_BY_ATTR, user);
    }

}

