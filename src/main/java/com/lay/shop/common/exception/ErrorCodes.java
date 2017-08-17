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
package com.lay.shop.common.exception;

public enum ErrorCodes {
    
    RESULT_OK("1", "成功"),
    RESULT_NO("0", "失败"),
    USER_NO("2","用户名或密码错误"),
    USER_DISABLE("3","用户被禁用"),
    LOGIN_NAME_NO("4","登录名不能为空"),
    LOGIN_PASSWORD_NO("5","登录密码不能为空"),
    ORG_CODE_NO("6","组织编码不能为空"),    
    
    /**文件相关异常 用10打头  异常编码改为6位编码*/
    FILE_IS_EMPTY("100001","文件内容为空");
    private String value;
    
    private String msg;
    
    private ErrorCodes(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
    
}
