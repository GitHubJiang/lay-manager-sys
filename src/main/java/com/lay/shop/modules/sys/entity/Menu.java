package com.lay.shop.modules.sys.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lay.shop.common.persistence.BaseModel;
/**
 * 
 * @author Lay
 * @date 2017年6月14日 下午4:56:36
 * @since
 */
public class Menu extends BaseModel {
	
	/** */
    private static final long serialVersionUID = -1898201981740145979L;
    
	/** 父级编号 */
	private Long parentId;
	/** 所有父级编号 */
	private String parentIds;
	/** 名称 */
	private String name;
	/** 链接 */
	private String href;
	/** 图标 */
	private String icon;
	/** 1.主菜单  2.子菜单 */
	private Integer type;
	/** 备注信息 */
	private String remarks;
	/** 1.正常；2.已删除  */
	private Integer lifecycle;

	public Menu(){
	}

	public Menu(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setParentId(Long value) {
		this.parentId = value;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
	public void setParentIds(String value) {
		this.parentIds = value;
	}
	
	public String getParentIds() {
		return this.parentIds;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setHref(String value) {
		this.href = value;
	}
	
	public String getHref() {
		return this.href;
	}
	public void setIcon(String value) {
		this.icon = value;
	}
	
	public String getIcon() {
		return this.icon;
	}
	
	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setRemarks(String value) {
		this.remarks = value;
	}
	
	public String getRemarks() {
		return this.remarks;
	}
	
    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("Id",getId())		
		.append("ParentId",getParentId())		
		.append("ParentIds",getParentIds())		
		.append("Name",getName())		
		.append("Href",getHref())		
		.append("Icon",getIcon())		
		.append("Type",getType())		
		.append("Remarks",getRemarks())		
		.append("Lifecycle",getLifecycle())		
			.toString();
	}
    @Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(getId())
		.append(getParentId())
		.append(getParentIds())
		.append(getName())
		.append(getHref())
		.append(getIcon())
		.append(getType())
		.append(getRemarks())
		.append(getLifecycle())
			.toHashCode();
	}
    @Override
	public boolean equals(Object obj) {
		if(obj instanceof Menu == false) return false;
		if(this == obj) return true;
		Menu other = (Menu)obj;
		return new EqualsBuilder()
		.append(getId(),other.getId())

		.append(getParentId(),other.getParentId())

		.append(getParentIds(),other.getParentIds())

		.append(getName(),other.getName())

		.append(getHref(),other.getHref())

		.append(getIcon(),other.getIcon())

		.append(getType(),other.getType())

		.append(getRemarks(),other.getRemarks())

		.append(getLifecycle(),other.getLifecycle())

			.isEquals();
	}
}

