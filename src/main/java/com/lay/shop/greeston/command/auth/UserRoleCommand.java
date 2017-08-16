package com.lay.shop.greeston.command.auth;

import java.io.Serializable;

public class UserRoleCommand implements Serializable {

    /** */
    private static final long serialVersionUID = -7517496452880341872L;
    /** 用户角色组织表 */
    private Long id;
    /** 所属组织ID */
    private Long ouId;
    /** 所属组织名称 */
    private String ouName;
    /** 组织类型 ID*/
    private Long ouTypeId;
    /** 组织类型 名称*/
    private Long ouTypeName;
    /** 角色id */
    private Long roleId;
    /** 角色名 */
    private String roleName;
    
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

    public String getOuName() {
        return ouName;
    }

    public void setOuName(String ouName) {
        this.ouName = ouName;
    }

    public Long getOuTypeId() {
        return ouTypeId;
    }

    public void setOuTypeId(Long ouTypeId) {
        this.ouTypeId = ouTypeId;
    }

    public Long getOuTypeName() {
        return ouTypeName;
    }

    public void setOuTypeName(Long ouTypeName) {
        this.ouTypeName = ouTypeName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
