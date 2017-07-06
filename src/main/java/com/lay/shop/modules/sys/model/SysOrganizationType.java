package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;
/**
 * 
 * @author Lay
 * @date 2017年7月6日 下午7:16:51
 * @since
 */
public class SysOrganizationType extends BaseModel {
	
	
	/** */
    private static final long serialVersionUID = 4724868189302733327L;
    /** 组织类型编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 描述 */
	private String description;
	/** 父级组织类型ID */
	private Long parentOutId;
	//columns END

	public SysOrganizationType(){
	}

	

	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setParentOutId(Long value) {
		this.parentOutId = value;
	}
	
	public Long getParentOutId() {
		return this.parentOutId;
	}
    
}

