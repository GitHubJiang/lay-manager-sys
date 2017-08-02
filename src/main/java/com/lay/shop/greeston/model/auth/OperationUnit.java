package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/***
 * @description 组织信息
 * @author Lay
 * @date 2017年8月2日 下午2:47:10
 * @since
 */
public class OperationUnit extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 6402345915990121756L;
    /** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 1.可用;2.已禁用(无效);3.已删除 */
	private Integer lifecycle;
	/** 组织类型:1.系统;2.品牌;3.渠道 */
	private Long ouTypeId;
	/** 父组织ID */
	private Long parentId;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 修改时间 */
	private java.util.Date version;
	//columns END

	public OperationUnit(){
	}

	public OperationUnit(
		java.lang.Long id
	){
		this.id = id;
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
	public void setLifecycle(Integer value) {
		this.lifecycle = value;
	}
	
	public Integer getLifecycle() {
		return this.lifecycle;
	}
	public void setOuTypeId(Long value) {
		this.ouTypeId = value;
	}
	
	public Long getOuTypeId() {
		return this.ouTypeId;
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
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setVersion(java.util.Date value) {
		this.version = value;
	}
	
	public java.util.Date getVersion() {
		return this.version;
	}
    
}

