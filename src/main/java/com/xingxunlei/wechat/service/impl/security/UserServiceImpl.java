/**
 * UserServiceImpl.java
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

import java.util.List;

import com.xingxunlei.wechat.dao.security.UserDAO;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import com.xingxunlei.wechat.model.cms.security.UserModel;

import com.xingxunlei.wechat.service.security.UserService;

/**
 * ClassName:UserServiceImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:44:41
 *
 * @see 	 
 */
public class UserServiceImpl implements UserService {
    
    @Autowired
    @Qualifier("userDAOImpl")
    private UserDAO userDAO;

    @Transactional(readOnly=true) 
    public UserModel getByAccount(String username) {
        //封装查询参数
        UserModel user = new UserModel();
        user.setLoginName(username);
        List<UserModel> results = findUserByAttr(user);
        if(results.size()>0) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    public List<UserModel> findUserByAttr(UserModel user) {
        return userDAO.findUserByAttr(user);
    }

    @Override
    public void updateLoginTime(Integer id) {
    }

}

