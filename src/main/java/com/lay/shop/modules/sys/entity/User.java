package com.lay.shop.modules.sys.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lay.shop.common.persistence.BaseModel;
/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:56:38
 * @since
 */
public class User extends BaseModel {
    
    /** */
    private static final long serialVersionUID = 4585232726865864583L;
    /** 归属公司 */
    private Long companyId;
    /** 归属部门 */
    private Long officeId;
    /** 登录名 */
    private String loginName;
    /** 密码 */
    private String password;
    /** 工号 */
    private String jobNo;
    /** 姓名 */
    private String name;
    /** 邮箱 */
    private String email;
    /** 电话 */
    private String phone;
    /** 手机 */
    private String mobile;
    /** 用户类型: 1.管理员 2.普通用户 */
    private Boolean userType;
    /** 用户头像 */
    private String photo;
    /** 最后登陆IP */
    private String loginIp;
    /** 最后登陆时间 */
    private java.util.Date loginTime;
    /** 是否可登录 */
    private String loginFlag;
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
    

    public User(){
    }

    public User(
        java.lang.Long id
    ){
        this.id = id;
    }

    public void setCompanyId(Long value) {
        this.companyId = value;
    }
    
    public Long getCompanyId() {
        return this.companyId;
    }
    public void setOfficeId(Long value) {
        this.officeId = value;
    }
    
    public Long getOfficeId() {
        return this.officeId;
    }
    public void setLoginName(String value) {
        this.loginName = value;
    }
    
    public String getLoginName() {
        return this.loginName;
    }
    public void setPassword(String value) {
        this.password = value;
    }
    
    public String getPassword() {
        return this.password;
    }
    public void setJobNo(String value) {
        this.jobNo = value;
    }
    
    public String getJobNo() {
        return this.jobNo;
    }
    public void setName(String value) {
        this.name = value;
    }
    
    public String getName() {
        return this.name;
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
    public void setMobile(String value) {
        this.mobile = value;
    }
    
    public String getMobile() {
        return this.mobile;
    }
    public void setUserType(Boolean value) {
        this.userType = value;
    }
    
    public Boolean getUserType() {
        return this.userType;
    }
    public void setPhoto(String value) {
        this.photo = value;
    }
    
    public String getPhoto() {
        return this.photo;
    }
    public void setLoginIp(String value) {
        this.loginIp = value;
    }
    
    public String getLoginIp() {
        return this.loginIp;
    }
    
    public void setLoginTime(java.util.Date value) {
        this.loginTime = value;
    }
    
    public java.util.Date getLoginTime() {
        return this.loginTime;
    }
    public void setLoginFlag(String value) {
        this.loginFlag = value;
    }
    
    public String getLoginFlag() {
        return this.loginFlag;
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
        .append("CompanyId",getCompanyId())     
        .append("OfficeId",getOfficeId())       
        .append("LoginName",getLoginName())     
        .append("Password",getPassword())       
        .append("JobNo",getJobNo())     
        .append("Name",getName())       
        .append("Email",getEmail())     
        .append("Phone",getPhone())     
        .append("Mobile",getMobile())       
        .append("UserType",getUserType())       
        .append("Photo",getPhoto())     
        .append("LoginIp",getLoginIp())     
        .append("LoginTime",getLoginTime())     
        .append("LoginFlag",getLoginFlag())     
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
        .append(getCompanyId())
        .append(getOfficeId())
        .append(getLoginName())
        .append(getPassword())
        .append(getJobNo())
        .append(getName())
        .append(getEmail())
        .append(getPhone())
        .append(getMobile())
        .append(getUserType())
        .append(getPhoto())
        .append(getLoginIp())
        .append(getLoginTime())
        .append(getLoginFlag())
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
        if(obj instanceof User == false) return false;
        if(this == obj) return true;
        User other = (User)obj;
        return new EqualsBuilder()
        .append(getId(),other.getId())

        .append(getCompanyId(),other.getCompanyId())

        .append(getOfficeId(),other.getOfficeId())

        .append(getLoginName(),other.getLoginName())

        .append(getPassword(),other.getPassword())

        .append(getJobNo(),other.getJobNo())

        .append(getName(),other.getName())

        .append(getEmail(),other.getEmail())

        .append(getPhone(),other.getPhone())

        .append(getMobile(),other.getMobile())

        .append(getUserType(),other.getUserType())

        .append(getPhoto(),other.getPhoto())

        .append(getLoginIp(),other.getLoginIp())

        .append(getLoginTime(),other.getLoginTime())

        .append(getLoginFlag(),other.getLoginFlag())

        .append(getCreateBy(),other.getCreateBy())

        .append(getCreateTime(),other.getCreateTime())

        .append(getUpdateBy(),other.getUpdateBy())

        .append(getUpdateTime(),other.getUpdateTime())

        .append(getRemarks(),other.getRemarks())

        .append(getLifecycle(),other.getLifecycle())

            .isEquals();
    }
}

