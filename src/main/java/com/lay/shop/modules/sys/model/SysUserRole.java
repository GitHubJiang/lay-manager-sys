package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;

/**
 * 
 * @author Lay
 * @date 2017年7月6日 下午7:29:15
 * @since
 */
public class SysUserRole extends BaseModel {
	
	/** */
    private static final long serialVersionUID = -5959587188876903642L;
    /** 用户ID */
	private Long userId;
	/** 角色ID */
	private Long roleId;

	public SysUserRole(){
	}


	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	public void setRoleId(Long value) {
		this.roleId = value;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}
    
}

