/**
 * BaseModel.java
 * com.xingxunlei.wechat.model.cms
 *
 * Function： BaseModel
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.model.cms;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:BaseModel
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-22 下午7:00:34
 * 
 * @see
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -771584835734141394L;

    protected Integer id;
    protected Integer createBy;
    protected String createName;
    protected Date createTime;
    protected Integer modifyBy;
    protected String modifyName;
    protected Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
