/**
 * RoleDAOImpl.java
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

import java.util.HashMap;
import java.util.Map;

import java.util.List;

import com.xingxunlei.wechat.dao.impl.BaseDAO;
import com.xingxunlei.wechat.dao.security.RoleDAO;

/**
 * ClassName:RoleDAOImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:37:32
 *
 * @see 	 
 */
public class RoleDAOImpl extends BaseDAO implements RoleDAO {
    
    private static final String GET_USER_PERMISSIONS_BY_USERID = "com.xingxunlei.wechat.security.role.getHasPermissByUser";

    @Override
    public List<String> getHasPermissByUserID(Integer userId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", userId);
        return sqlSession.selectList(GET_USER_PERMISSIONS_BY_USERID, map);
    }

}

