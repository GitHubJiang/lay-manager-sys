package com.lay.shop.modules.sys.command;

import java.io.Serializable;

public class UserCommand implements Serializable{

    /** */
    private static final long serialVersionUID = 6009775100711138778L;
    /** 登录名 */
    private String loginName;
    /** 用户密码 */
    private String password;
    /** 用户工号 */
    private String jobNo;
    /** 用户名 */
    private String name;
    /** 邮箱 */
    private String email;
    /** 固定电话 */
    private String phone;
    /** 手机号 */
    private String mobile;
    /** 用户备注 */
    private String remarks;
    /** 公司名称 */
    private String companyName;
    /** 公司编码 */
    private String companyCode;
    /** 部门名称 */
    private String officeName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }


}
