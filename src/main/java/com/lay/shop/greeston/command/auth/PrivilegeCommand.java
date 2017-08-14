/**
 * Copyright (c) 2015 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */
package com.lay.shop.greeston.command.auth;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.lay.shop.greeston.model.auth.PrifunUrl;

public class PrivilegeCommand implements Serializable {

    /** */
    private static final long serialVersionUID = -1289016696310437904L;
    /** 权限id */
    private Long id;
    /** 权限编码，唯一 */
    private String acl;
    /** 权限名称 */
    private String name;
    /** 组织类型ID */
    private Long ouTypeId;
    /** 组织类型名称 */
    private String ouTypeName;
    /** 备注 */
    private String remark;
    /** 修改时间 */
    private java.util.Date version;    
    /** 权限功能URL关联信息 */
    private List<PrifunUrl> priFuns;
    /** 前端的传过来的  权限功能URL关联信息 */
    private List<String> rps;
    /**提供给前端渲染页面用的*/
    private Map<Long,List<String>> priFunMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOuTypeId() {
        return ouTypeId;
    }

    public void setOuTypeId(Long ouTypeId) {
        this.ouTypeId = ouTypeId;
    }

    public String getOuTypeName() {
        return ouTypeName;
    }

    public void setOuTypeName(String ouTypeName) {
        this.ouTypeName = ouTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public java.util.Date getVersion() {
        return version;
    }

    public void setVersion(java.util.Date version) {
        this.version = version;
    }

    public List<PrifunUrl> getPriFuns() {
        return priFuns;
    }

    public void setPriFuns(List<PrifunUrl> priFuns) {
        this.priFuns = priFuns;
    }

    public List<String> getRps() {
        return rps;
    }

    public void setRps(List<String> rps) {
        this.rps = rps;
    }

    public Map<Long, List<String>> getPriFunMap() {
        return priFunMap;
    }

    public void setPriFunMap(Map<Long, List<String>> priFunMap) {
        this.priFunMap = priFunMap;
    }


}
