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
package com.lay.shop.common.constants;

public final class AuthConstants {
    
    private AuthConstants(){};

    /**
     * 左菜单
     */
    public static final String MENU_ITEMS = "menuItems";
    
    /**
     * 组织列表
     */
    public static final String ORG_LIST = "unitTree";
    
    /**
     * 当前组织类型
     */
    public static final String ORG_TYPE = "orgType";
    
    /**
     * cookie名称的前缀，此cookie用来存放用户上次退出时使用的组织
     */
    public static final String COOKIE_NAME_PREFIX="orgId_";
}
