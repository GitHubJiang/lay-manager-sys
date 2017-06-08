/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lay.shop.common.persistence;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lay.shop.common.persistence.page.Pagination;
import com.lay.shop.modules.sys.entity.User;
import com.lay.shop.modules.sys.utils.UserUtils;

/**
 * Entity支持类
 * @author Lay
 * @version 2017-06-08
 */
public abstract class BaseEntity<T> implements Serializable {

	/** */
    private static final long serialVersionUID = -4839571464106514051L;

    /**
	 * 实体编号（唯一标识）
	 */
	protected Long id;
	
	/**
	 * 当前用户
	 */
	protected User currentUser;
	
	/**
	 * 当前实体分页对象
	 */
	protected Pagination<T> page;

	public BaseEntity() {
		
	}
	
	public BaseEntity(Long id) {
		this();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		if(currentUser == null){
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@JsonIgnore
	@XmlTransient
	public Pagination<T> getPage() {
		if (page == null){
			page = new Pagination<T>();
		}
		return page;
	}
	
	public Pagination<T> setPage(Pagination<T> page) {
		this.page = page;
		return page;
	}
	
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();

	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
	
}
