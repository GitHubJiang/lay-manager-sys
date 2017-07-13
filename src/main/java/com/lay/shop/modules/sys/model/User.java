package com.lay.shop.modules.sys.model;
import com.lay.shop.common.persistence.BaseModel;
/**
 * 
 * @author larkark
 *
 */
public class User extends BaseModel {
	
	/** */
    private static final long serialVersionUID = 3598127183719112121L;

    /** 所属组织ID */
	private Long orgId;
	/** 登录名 */
	private String loginName;
	/** 工号 */
	private String jobNo;
	/** 用户姓名 */
	private String userName;
	/** 密码 */
	private String loginPassword;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 邮箱 */
	private String email;
	/** 手机号 */
	private String phone;
	/** 1.可用;2.已删除 */
	private Integer lifecycle;


	public User(){
	}

	public User(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setOrgId(Long value) {
		this.orgId = value;
	}
	
	public Long getOrgId() {
		return this.orgId;
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName;
	}
	public void setJobNo(String value) {
		this.jobNo = value;
	}
	
	public String getJobNo() {
		return this.jobNo;
	}
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setLoginPassword(String value) {
		this.loginPassword = value;
	}
	
	public String getLoginPassword() {
		return this.loginPassword;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
	}
	public void setLifecycle(Integer value) {
		this.lifecycle = value;
	}
	
	public Integer getLifecycle() {
		return this.lifecycle;
	}
    
}

