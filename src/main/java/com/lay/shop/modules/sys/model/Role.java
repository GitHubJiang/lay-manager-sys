package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;
/***
 * 
 * @author Lay
 * @date 2017年7月6日 下午7:20:36
 * @since
 */
public class Role extends BaseModel {
	
	
	/** */
    private static final long serialVersionUID = 7294159567960943066L;
    /** 名称 */
	private String name;
	/** 组织类型的ID */
	private Long orgTypeId;
	/** 1.正常；2.已删除 */
	private Integer lifecycle;
	/** 权限的类型，1.操作权限;2.功能权限;3.工作流权限 */
	private Integer type;
	//columns END

	public Role(){
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setOrgTypeId(Long value) {
		this.orgTypeId = value;
	}
	
	public Long getOrgTypeId() {
		return this.orgTypeId;
	}
	public void setLifecycle(Integer value) {
		this.lifecycle = value;
	}
	
	public Integer getLifecycle() {
		return this.lifecycle;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
    
}

