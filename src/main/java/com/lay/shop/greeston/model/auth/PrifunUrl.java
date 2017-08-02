package com.lay.shop.greeston.model.auth;


import com.lay.shop.common.persistence.BaseModel;

/***
 * 权限功能
 * @author Lay
 * @date 2017年8月2日 下午3:03:28
 * @since
 */
public class PrifunUrl extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 6841804537845926720L;
    /** 权限编码 */
	private String acl;
	/** 权限功能：view.查看;add.新增;update.修改;remove.删除; */
	private String funCode;
	/** URL对应的ID */
	private Long urlId;
	
	public PrifunUrl(){
	}

	public PrifunUrl(
		java.lang.Long id
	){
		this.id = id;
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
	public void setUrlId(Long value) {
		this.urlId = value;
	}
	
	public Long getUrlId() {
		return this.urlId;
	}
    
}

