package com.lay.shop.modules.sys.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lay.shop.common.persistence.BaseModel;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:47:28
 * @since
 */
public class Organization extends BaseModel {
    
    
    /** */
    private static final long serialVersionUID = -5387481494110870667L;
    /** 父级编号 */
    private Long parentId;
    /** 所有父级编号 */
    private String parentIds;
    /** 机构编码 */
    private String organizationCode;
    /** 名称 */
    private String name;
    /** 排序号 */
    private Integer sort;
    /** 机构类型 1:公司,2:部门,3:小组 */
    private Boolean type;
    /** 联系地址 */
    private String address;
    /** 邮政编码 */
    private String zipCode;
    /** 电话 */
    private String phone;
    /** 邮箱 */
    private String email;
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

    public Organization(){
    }

    public Organization(
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
    public void setOrganizationCode(String value) {
        this.organizationCode = value;
    }
    
    public String getOrganizationCode() {
        return this.organizationCode;
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
    public void setType(Boolean value) {
        this.type = value;
    }
    
    public Boolean getType() {
        return this.type;
    }
    public void setAddress(String value) {
        this.address = value;
    }
    
    public String getAddress() {
        return this.address;
    }
    public void setZipCode(String value) {
        this.zipCode = value;
    }
    
    public String getZipCode() {
        return this.zipCode;
    }
    public void setPhone(String value) {
        this.phone = value;
    }
    
    public String getPhone() {
        return this.phone;
    }
    public void setEmail(String value) {
        this.email = value;
    }
    
    public String getEmail() {
        return this.email;
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
        .append("OrganizationCode",getOrganizationCode())       
        .append("Name",getName())       
        .append("Sort",getSort())       
        .append("Type",getType())       
        .append("Address",getAddress())     
        .append("ZipCode",getZipCode())     
        .append("Phone",getPhone())     
        .append("Email",getEmail())     
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
        .append(getOrganizationCode())
        .append(getName())
        .append(getSort())
        .append(getType())
        .append(getAddress())
        .append(getZipCode())
        .append(getPhone())
        .append(getEmail())
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
        if(obj instanceof Organization == false) return false;
        if(this == obj) return true;
        Organization other = (Organization)obj;
        return new EqualsBuilder()
        .append(getId(),other.getId())

        .append(getParentId(),other.getParentId())

        .append(getParentIds(),other.getParentIds())

        .append(getOrganizationCode(),other.getOrganizationCode())

        .append(getName(),other.getName())

        .append(getSort(),other.getSort())

        .append(getType(),other.getType())

        .append(getAddress(),other.getAddress())

        .append(getZipCode(),other.getZipCode())

        .append(getPhone(),other.getPhone())

        .append(getEmail(),other.getEmail())

        .append(getCreateBy(),other.getCreateBy())

        .append(getCreateTime(),other.getCreateTime())

        .append(getUpdateBy(),other.getUpdateBy())

        .append(getUpdateTime(),other.getUpdateTime())

        .append(getRemarks(),other.getRemarks())

        .append(getLifecycle(),other.getLifecycle())

            .isEquals();
    }
}

