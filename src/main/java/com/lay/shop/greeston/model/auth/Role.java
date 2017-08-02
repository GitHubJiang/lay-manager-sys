package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/**
 * 角色信息
 * @author Lay
 * @date 2017年8月2日 下午3:05:33
 * @since
 */
public class Role extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 4150397730319720179L;
    /** 名称 */
	private String name;
	/** 组织类型Id */
	private Long ouTypeId;
	/** 1.可用;2.已禁用(无效);3.已删除 */
	private Integer lifecycle;

	public Role(){
	}

	public Role(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setOuTypeId(Long value) {
		this.ouTypeId = value;
	}
	
	public Long getOuTypeId() {
		return this.ouTypeId;
	}
	public void setLifecycle(Integer value) {
		this.lifecycle = value;
	}
	
	public Integer getLifecycle() {
		return this.lifecycle;
	}
    
}

