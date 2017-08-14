/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.lay.shop.greeston.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lay.shop.greeston.command.auth.UserDetailsCommand;

/***
 * 
 * @author Lay
 * @date 2017年8月2日 下午6:58:45
 * @since
 */
public abstract class BaseController{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
     * 获取当前登录用户信息
     * 
     * @return
     */
    protected UserDetailsCommand getCurrentUserDetails(){
        UserDetailsCommand user = (UserDetailsCommand) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
    
    
}
