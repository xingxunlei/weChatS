package com.xingxunlei.wechat.model.cms.security;

import com.xingxunlei.wechat.model.cms.BaseModel;

public class UserRoleModel extends BaseModel {
    
    private static final long serialVersionUID = 6585220187919307936L;

    private Integer roleId;

    private Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}