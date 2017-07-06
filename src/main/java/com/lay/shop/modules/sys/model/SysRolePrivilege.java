 package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;

public class SysRolePrivilege extends BaseModel {
	
	
	/** */
    private static final long serialVersionUID = -3502956712356574349L;
    /** 角色ID */
	private Long roleId;
	/** 权限的ACL */
	private String acl;

	public SysRolePrivilege(){
	}

	public void setRoleId(Long value) {
		this.roleId = value;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}
	public void setAcl(String value) {
		this.acl = value;
	}
	
	public String getAcl() {
		return this.acl;
	}
    
}

