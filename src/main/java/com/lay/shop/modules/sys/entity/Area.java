package com.lay.shop.modules.sys.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lay.shop.common.persistence.BaseModel;
/***
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:52:33
 * @since
 */
public class Area extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 4508879918929594347L;
    /** 父级编号 */
	private Long parentId;
	/** 所有父级编号 */
	private String parentIds;
	/** 名称 */
	private String name;
	/** 排序 */
	private Integer sort;
	/** 区域编码  */
	private String code;
	/** 区域类型 1.国家 2.省份、直辖市 3.地市 4.区县 */
	private Boolean type;
	/** 创建者 */
	private Long createBy;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新者 */
	private Long updateBy;
	/** 更新时间 */
	private java.util.Date updateTime;
	/** 备注信息 */
	private String remarks;
	/** 1.正常；2.已删除  */
	private Boolean lifecycle;

	public Area(){
	}

	public Area(
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
	public void setSort(Integer value) {
		this.sort = value;
	}
	
	public Integer getSort() {
		return this.sort;
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setType(Boolean value) {
		this.type = value;
	}
	
	public Boolean getType() {
		return this.type;
	}
	public void setCreateBy(Long value) {
		this.createBy = value;
	}
	
	public Long getCreateBy() {
		return this.createBy;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateBy(Long value) {
		this.updateBy = value;
	}
	
	public Long getUpdateBy() {
		return this.updateBy;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setRemarks(String value) {
		this.remarks = value;
	}
	
	public String getRemarks() {
		return this.remarks;
	}
	public void setLifecycle(Boolean value) {
		this.lifecycle = value;
	}
	
	public Boolean getLifecycle() {
		return this.lifecycle;
	}
    @Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("Id",getId())		
		.append("ParentId",getParentId())		
		.append("ParentIds",getParentIds())		
		.append("Name",getName())		
		.append("Sort",getSort())		
		.append("Code",getCode())		
		.append("Type",getType())		
		.append("CreateBy",getCreateBy())		
		.append("CreateTime",getCreateTime())		
		.append("UpdateBy",getUpdateBy())		
		.append("UpdateTime",getUpdateTime())		
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
		.append(getSort())
		.append(getCode())
		.append(getType())
		.append(getCreateBy())
		.append(getCreateTime())
		.append(getUpdateBy())
		.append(getUpdateTime())
		.append(getRemarks())
		.append(getLifecycle())
			.toHashCode();
	}
    @Override
	public boolean equals(Object obj) {
		if(obj instanceof Area == false) return false;
		if(this == obj) return true;
		Area other = (Area)obj;
		return new EqualsBuilder()
		.append(getId(),other.getId())

		.append(getParentId(),other.getParentId())

		.append(getParentIds(),other.getParentIds())

		.append(getName(),other.getName())

		.append(getSort(),other.getSort())

		.append(getCode(),other.getCode())

		.append(getType(),other.getType())

		.append(getCreateBy(),other.getCreateBy())

		.append(getCreateTime(),other.getCreateTime())

		.append(getUpdateBy(),other.getUpdateBy())

		.append(getUpdateTime(),other.getUpdateTime())

		.append(getRemarks(),other.getRemarks())

		.append(getLifecycle(),other.getLifecycle())

			.isEquals();
	}
}

