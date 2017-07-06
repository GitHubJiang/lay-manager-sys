package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;
/***
 * 
 * @author Lay
 * @date 2017年7月6日 下午7:13:47
 * @since
 */
public class SysOrganization extends BaseModel {
	
	
	/** */
    private static final long serialVersionUID = -9190175869435868018L;
    /** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 全称 */
	private String fullName;
	/** 组织类型ID */
	private Long orgTypeId;
	/** 父组织ID */
	private Long parentId;
	/** 备注 */
	private String remark;
	/** 1.可用;2.已删除 */
	private Integer lifecycle;
	/** 最后修改时间 */
	private java.util.Date lastModifyTime;

	public SysOrganization(){
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
	public void setFullName(String value) {
		this.fullName = value;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	public void setOrgTypeId(Long value) {
		this.orgTypeId = value;
	}
	
	public Long getOrgTypeId() {
		return this.orgTypeId;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	public void setLifecycle(Integer value) {
		this.lifecycle = value;
	}
	
	public Integer getLifecycle() {
		return this.lifecycle;
	}

    public java.util.Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(java.util.Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
	
}

