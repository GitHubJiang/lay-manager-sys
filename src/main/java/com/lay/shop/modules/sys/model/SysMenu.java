package com.lay.shop.modules.sys.model;


import com.lay.shop.common.persistence.BaseModel;
/**
 * 
 * @author Lay
 * @date 2017年7月6日 下午7:08:46
 * @since
 */
public class SysMenu extends BaseModel {
	
	
	/** */
    private static final long serialVersionUID = -6989001149868597249L;
    /** 名称 */
	private String name;
	/** 权限ACL */
	private String acl;
	/** URL */
	private String url;
	/** 序号 */
	private Integer sortNo;
	/** 父菜单 */
	private Long parentId;
	
	public SysMenu(){
	}

	public SysMenu(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setAcl(String value) {
		this.acl = value;
	}
	
	public String getAcl() {
		return this.acl;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}
	public void setSortNo(Integer value) {
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
    
}

