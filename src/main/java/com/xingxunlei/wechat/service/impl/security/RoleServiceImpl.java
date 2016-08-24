/**
 * RoleServiceImpl.java
 * com.xingxunlei.wechat.service.impl.security
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.service.impl.security;

import com.xingxunlei.wechat.dao.security.RoleDAO;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.xingxunlei.wechat.service.security.RoleService;

/**
 * ClassName:RoleServiceImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:43:58
 *
 * @see 	 
 */
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    @Qualifier("roleDAOImpl")
    private RoleDAO roleDAO;

    @Override
    public List<String> getHasPermissByUserID(Integer userId) {
        return roleDAO.getHasPermissByUserID(userId);
    }

}

