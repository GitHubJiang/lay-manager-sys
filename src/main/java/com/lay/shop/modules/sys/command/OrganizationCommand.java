package com.lay.shop.modules.sys.command;

import java.io.Serializable;

public class OrganizationCommand implements Serializable {

    /** */
    private static final long serialVersionUID = -1569022031964995736L;
    /** 所属组织ID */
    private Long id;
    /** 组织编码 */
    private String code;
    /** 组织名称 */
    private String name;
    /** 组织全称 */
    private String fullName;
    /** 1.可用;2.已删除 */
    private Integer lifecycle;
    /**组织类型ID*/
    private Long orgTypeId;
    /** 组织类型名称 */
    private String orgTypeName;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Long getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(Long orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

}
