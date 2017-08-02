package com.lay.shop.greeston.command.auth;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lay.shop.greeston.serializer.MenuSerializer;

/**
 * 菜单
 * @author 李辉
 */
@JsonSerialize(using = MenuSerializer.class)
public class MenuCommand implements Serializable {

	private static final long serialVersionUID = 3082005621490303311L;
	
	/** ID */
    private Long id;
    
    /** 菜单显示名称 */
    private String name;

    /** 入口URL，对于菜单组/分隔符忽略此数据 */
    private String url;
    
    /** 序号，仅对同级菜单排序用 */
    private Integer sortNo;
    
    /** 是否有子菜单[菜单组] */
    private Boolean isGroup = Boolean.FALSE;

    /** 权限ACL */
    private String acl;

    /** 父菜单项，如果为空说明是顶层菜单 */
    private Long parentId;
    
    /** 子菜单     */
    private List<MenuCommand> childList;

    private Long appId;
    
    private Integer lifecycle;
    
	public MenuCommand() {	}

	

	public MenuCommand(Long id, String name, String url, Integer sortNo,
			Boolean isGroup, String acl, Long parentId,
			List<MenuCommand> childList) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.sortNo = sortNo;
		this.isGroup = isGroup;
		this.acl = acl;
		this.parentId = parentId;
		this.childList = childList;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<MenuCommand> getChildList() {
		return childList;
	}

	public void setChildList(List<MenuCommand> childList) {
		this.childList = childList;
	}



	public String getAcl() {
		return acl;
	}



	public void setAcl(String acl) {
		this.acl = acl;
	}

	/**
	 * appId的获取.
	 * @return Long
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * 设定appId的值.
	 * @param Long
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	/**
	 * lifecycle的获取.
	 * @return Integer
	 */
	public Integer getLifecycle() {
		return lifecycle;
	}



	/**
	 * 设定lifecycle的值.
	 * @param Integer
	 */
	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}



	@Override
	public String toString() {
		return "MenuItemCommand [id=" + id + ", name=" + name + ", url=" + url
				+ ", sortNo=" + sortNo + ", isGroup=" + isGroup + ", acl="
				+ acl + ", parentId=" + parentId + ", childList=" + childList
				+ "]";
	}

	
    
	
    
}
