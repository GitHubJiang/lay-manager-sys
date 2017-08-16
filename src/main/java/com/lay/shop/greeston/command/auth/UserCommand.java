package com.lay.shop.greeston.command.auth;

import java.io.Serializable;
import java.util.List;

public class UserCommand implements Serializable {

    /** */
    private static final long serialVersionUID = -8253602986706530941L;
    /** 所属组织ID */
    private Long ouId;
    /** 所属组织类型*/
    private Integer ouType;
    /** 用户ID */
    private Long id;
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
    
    List<UserRoleCommand> list;   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOuId() {
        return ouId;
    }

    public void setOuId(Long ouId) {
        this.ouId = ouId;
    }

    public Integer getOuType() {
        return ouType;
    }

    public void setOuType(Integer ouType) {
        this.ouType = ouType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<UserRoleCommand> getList() {
        return list;
    }

    public void setList(List<UserRoleCommand> list) {
        this.list = list;
    }

    

}
