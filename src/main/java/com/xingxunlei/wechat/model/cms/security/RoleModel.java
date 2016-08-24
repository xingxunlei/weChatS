package com.xingxunlei.wechat.model.cms.security;

import com.xingxunlei.wechat.model.cms.BaseModel;

public class RoleModel extends BaseModel {
    
    private static final long serialVersionUID = -6393194238416720295L;

    private String roleName;

    private String systemCode;
    
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

}