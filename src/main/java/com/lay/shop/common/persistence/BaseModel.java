/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lay.shop.common.persistence;

import java.io.Serializable;

/**
 * Model基础类
 * @author Lay
 * @version 2017-06-08
 */
public abstract class BaseModel implements Serializable {

	/** */
    private static final long serialVersionUID = -4839571464106514051L;

    /**
	 * 实体编号（唯一标识）
	 */
	protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	
}
