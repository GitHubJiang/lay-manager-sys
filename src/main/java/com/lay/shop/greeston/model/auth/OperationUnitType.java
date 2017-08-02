package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

/**
 * 组织类型
 * @author Lay
 * @date 2017年8月2日 下午3:02:38
 * @since
 */
public class OperationUnitType extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 289863720567837385L;
    /** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 1.可用;2.已禁用(无效);3.已删除 */
	private Integer lifecycle;
	/** 父组织类型ID */
	private Long parentOutId;
	/** 备注 */
	private String remark;

	public OperationUnitType(){
	}

	public OperationUnitType(
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
	public void setParentOutId(Long value) {
		this.parentOutId = value;
	}
	
	public Long getParentOutId() {
		return this.parentOutId;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
    
}

