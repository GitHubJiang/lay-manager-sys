package com.lay.shop.modules.sys.command;

import java.io.Serializable;
import java.util.List;

public class UserCommand implements Serializable {

    /** */
    private static final long serialVersionUID = -1216895911541045764L;
    /** 用户ID */
    private Long id;    
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
    
    List<OrganizationCommand> organizationList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<OrganizationCommand> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<OrganizationCommand> organizationList) {
        this.organizationList = organizationList;
    }

    

}
