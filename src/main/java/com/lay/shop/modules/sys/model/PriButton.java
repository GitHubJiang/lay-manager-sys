package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;

/**
 * 
 * @author Lay
 * @date 2017年7月6日 下午6:46:05
 * @since
 */
public class PriButton extends BaseModel {
	
	
	/** */
    private static final long serialVersionUID = -3070390058144789443L;
    /** 权限ACL */
	private String acl;
	/** 权限功能CODE 新增、修改等按钮 */
	private String buttonCode;
	
	public PriButton(){
	}

	public void setAcl(String value) {
		this.acl = value;
	}
	
	public String getAcl() {
		return this.acl;
	}
	public void setButtonCode(String value) {
		this.buttonCode = value;
	}
	
	public String getButtonCode() {
		return this.buttonCode;
	}
    
}

