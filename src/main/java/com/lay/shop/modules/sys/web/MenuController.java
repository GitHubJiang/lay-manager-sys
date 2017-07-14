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
package com.lay.shop.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.common.web.BaseController;
import com.lay.shop.common.web.Result;
import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.service.MenuService;

@Controller
@RequestMapping("/sys/menu/")
public class MenuController extends BaseController {
    
    @Autowired
    private MenuService menuService;
    
    @RequestMapping(value = "menu")
    @ResponseBody
    public Result<List<MenuCommand>> menu(@RequestParam(required = false) Long pid,HttpServletRequest request) {
        
        UserCommand user = (UserCommand)request.getSession().getAttribute("user");        
        Result<List<MenuCommand>> result = new Result<>();
        List<MenuCommand> menu = null;
        try {
            if (Validator.isNullOrEmpty(pid)) {
                menu = menuService.findMainMenu(user.getId(), user.getOrgTypeCode());
            } else {
                menu = menuService.findLeftMenu(user.getId(), user.getOrgTypeCode(), pid);
            }
            result.setData(menu);
        } catch (Exception e) {
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
}
