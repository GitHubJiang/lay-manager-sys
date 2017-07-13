package com.lay.shop.modules.sys.model;

import com.lay.shop.common.persistence.BaseModel;
/***
 * 
 * @author Lay
 * @date 2017年7月6日 下午6:37:31
 * @since
 */
public class Privilege extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 8262371313294431449L;
    /** acl，唯一 */
	private String acl;
	/** 权限名称 */
	private String name;
	/** 组织类型ID 1.系统 2.公司 */
	private Long ouTypeId;
	/** 权限的类型，1.操作权限;2.功能权限;3.工作流权限 */
	private Integer type;
	/** 排序号 */
	private Integer sortNo;
	

	public Privilege(){
	}

	public Privilege(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setAcl(String value) {
		this.acl = value;
	}
	
	public String getAcl() {
		return this.acl;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setOuTypeId(Long value) {
		this.ouTypeId = value;
	}
	
	public Long getOuTypeId() {
		return this.ouTypeId;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setSortNo(Integer value) {
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo;
	}
    
}

