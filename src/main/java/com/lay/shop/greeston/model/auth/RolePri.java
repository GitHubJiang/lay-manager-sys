package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/***
 * 角色权限
 * @author Lay
 * @date 2017年8月2日 下午3:06:26
 * @since
 */
public class RolePri extends BaseModel {

	/** */
    private static final long serialVersionUID = 7848447921694157840L;
    /** 角色ID */
	private Long roleId;
	/** 权限的ACL */
	private String acl;
	/** 权限功能：view.查看;add.新增;update.修改;remove.删除; */
	private String funCode;
	
	public RolePri(){
	}

	public RolePri(
		java.lang.Long id
	){
		this.id = id;
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
	public void setFunCode(String value) {
		this.funCode = value;
	}
	
	public String getFunCode() {
		return this.funCode;
	}
    
}

