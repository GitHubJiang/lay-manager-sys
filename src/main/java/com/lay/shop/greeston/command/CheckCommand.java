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
package com.lay.shop.greeston.command;

import java.io.Serializable;

public class CheckCommand implements Serializable{

    /** */
    private static final long serialVersionUID = 4491616577785020444L;

    /**需校验的字段所属的表名*/
    private String table;
    /**是否是更新时校验字段重复性，id=null时表示新增时校验重复 
     * 其他情况都表示更新时校验字段重复性*/
    private Long id;
    /**需校验的字段名*/
    private String fieldName;
    /**需校验的字段名对应的值*/
    private String fieldValue;
    public String getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = table;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldValue() {
        return fieldValue;
    }
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
    
    
}
