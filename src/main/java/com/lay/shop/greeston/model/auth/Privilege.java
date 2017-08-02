package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/**
 * 权限信息
 * @author Lay
 * @date 2017年8月2日 下午3:04:36
 * @since
 */
public class Privilege extends BaseModel {
	

	/** */
    private static final long serialVersionUID = -8979846466984177791L;
    /** 权限编码，唯一 */
	private String acl;
	/** 权限名称 */
	private String name;
	/** 组织类型ID */
	private Long ouTypeId;
	/** 备注 */
	private String remark;
	/** 修改时间 */
	private java.util.Date version;
	//columns END

	public Privilege(){
	}

	public Privilege(
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
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	
	public void setVersion(java.util.Date value) {
		this.version = value;
	}
	
	public java.util.Date getVersion() {
		return this.version;
	}
    
}

