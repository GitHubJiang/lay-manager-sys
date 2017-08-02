package com.lay.shop.greeston.model.auth;

import com.lay.shop.common.persistence.BaseModel;

public class User extends BaseModel {
	
	/** */
    private static final long serialVersionUID = -2847078309925267615L;
    
	/** 所属组织ID */
	private Long ouId;
	/** 登录名 */
	private String loginName;
	/** 用户姓名 */
	private String userName;
	/** 员工工号 */
	private String jobNumber;
	/** 密码 */
	private String password;
	/** 邮箱 */
	private String email;
	/** 手机 */
	private String mobile;
	/** 1.可用;2.已禁用(无效);3.已删除 */
	private Integer lifecycle;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 最后修改时间 */
	private java.util.Date latestUpdateTime;
	/** 最后登录时间 */
	private java.util.Date latestAccessTime;
	/** 备注 */
	private String memo;
	
	public User(){
	}

	public User(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setOuId(Long value) {
		this.ouId = value;
	}
	
	public Long getOuId() {
		return this.ouId;
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName;
	}
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setJobNumber(String value) {
		this.jobNumber = value;
	}
	
	public String getJobNumber() {
		return this.jobNumber;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	public void setLifecycle(Integer value) {
		this.lifecycle = value;
	}
	
	public Integer getLifecycle() {
		return this.lifecycle;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setLatestUpdateTime(java.util.Date value) {
		this.latestUpdateTime = value;
	}
	
	public java.util.Date getLatestUpdateTime() {
		return this.latestUpdateTime;
	}
	
	public void setLatestAccessTime(java.util.Date value) {
		this.latestAccessTime = value;
	}
	
	public java.util.Date getLatestAccessTime() {
		return this.latestAccessTime;
	}
	public void setMemo(String value) {
		this.memo = value;
	}
	
	public String getMemo() {
		return this.memo;
	}
    
}

