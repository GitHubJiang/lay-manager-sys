package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;
/***
 * 
 * @author Lay
 * @date 2017年7月6日 下午7:26:51
 * @since
 */
public class UserOrg extends BaseModel {
	

	/** */
    private static final long serialVersionUID = -1752655221998022832L;
    /** 用户ID */
	private Long userId;
	/** 组织ID */
	private Long orgId;

	public UserOrg(){
	}


	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	public void setOrgId(Long value) {
		this.orgId = value;
	}
	
	public Long getOrgId() {
		return this.orgId;
	}
    
}

