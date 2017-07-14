package com.lay.shop.modules.sys.command;

import java.io.Serializable;

public class UserCommand implements Serializable {

    /** */
    private static final long serialVersionUID = -1216895911541045764L;
    /** 所属组织ID */
    private Long id;
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
    /** 邮箱 */
    private String email;
    /** 手机号 */
    private String phone;
    /** 状态 */
    private Integer lifecycle;
    /** 机构编码 */
    private String orgCode;
    /** 机构简称 */
    private String orgName;
    /** 机构全称 */
    private String orgFullName;
    /** 机构类型编码 */
    private String orgTypeCode;
    /** 机构类型名称 */
    private String orgTypeName;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public String getOrgTypeCode() {
        return orgTypeCode;
    }

    public void setOrgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

}
