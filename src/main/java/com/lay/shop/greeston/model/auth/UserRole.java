package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/***
 * 用户角色
 * @author Lay
 * @date 2017年8月2日 下午3:09:33
 * @since
 */
public class UserRole extends BaseModel {
		
	/** */
    private static final long serialVersionUID = -3605786024265110498L;
    /** 用户ID */
	private Long userId;
	/** 角色ID */
	private Long roleId;
	/** 组织ID */
	private Long ouId;

	public UserRole(){
	}

	public UserRole(
		java.lang.Long id
	){
		this.id = id;
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
	public void setOuId(Long value) {
		this.ouId = value;
	}
	
	public Long getOuId() {
		return this.ouId;
	}
   
}

