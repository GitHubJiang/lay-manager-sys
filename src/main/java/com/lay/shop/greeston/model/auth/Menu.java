package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/***
 * @description 菜单表
 * @author Lay
 * @date 2017年8月2日 下午2:45:54
 * @since
 */
public class Menu extends BaseModel {
	
	/** */
    private static final long serialVersionUID = -966358090080145082L;
    /** 名称 */
	private String name;
	/** 权限ACL */
	private String acl;
	/** URL */
	private String url;
	/** 序号 */
	private Integer sortNo;
	/** 是否有子菜单:0.没有;1.有 */
	private Integer isGroup;
	/** 父菜单 */
	private Long parentId;
	/** 创建时间 */
	private java.util.Date createTime;

	public Menu(){
	}

	public Menu(
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
	public void setIsGroup(Integer value) {
		this.isGroup = value;
	}
	
	public Integer getIsGroup() {
		return this.isGroup;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
   
}

