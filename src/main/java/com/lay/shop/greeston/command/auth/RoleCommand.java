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

public class RoleCommand implements Serializable {

    /** */
    private static final long serialVersionUID = -740440234034435640L;
    /** 角色ID */
    private Long id;
    /** 名称 */
    private String name;
    /** 组织类型Id */
    private Long ouTypeId;
    /** 组织类型Id */
    private String ouTypeName;
    /** 1.可用;2.已禁用(无效);3.已删除 */
    private Integer lifecycle;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Integer getLifecycle() {
        return lifecycle;
    }
    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }
    
    

}
