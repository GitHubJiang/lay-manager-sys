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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.common.web.BaseController;
import com.lay.shop.common.web.Result;
import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.model.Menu;
import com.lay.shop.modules.sys.service.MenuService;

@Controller
@RequestMapping("/sys/menu/")
public class MenuController extends BaseController {
    
    @Autowired
    private MenuService menuService;
    
    @RequestMapping(value = "menu")
    @ResponseBody
    public Result<List<MenuCommand>> menu(@RequestParam(required = false) Long pid,@RequestParam(required = false) String orgCode,HttpServletRequest request) {
        
        UserCommand user = (UserCommand)request.getSession().getAttribute("user");
        Result<List<MenuCommand>> result = new Result<>();
        List<MenuCommand> menu = null;
        try {
            if(Validator.isNullOrEmpty(orgCode)){
                throw new BusinessException(ErrorCodes.ORG_CODE_NO);
            }
            if (Validator.isNullOrEmpty(pid)) {
                menu = menuService.findMainMenu(user.getId(), orgCode);
            } else {
                menu = menuService.findLeftMenu(user.getId(), orgCode, pid);
            }
            result.setData(menu);
        } catch (BusinessException e) {
            logger.error("MenuController.BusinessException:{}",e.getMessage());
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        }
        catch (Exception e) {
            logger.error("MenuController.system.error:{}",e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value = "add")
    @ResponseBody
    public Result<Menu> save(Menu menu) {
             
        Result<Menu> result = new Result<>();
        try {
            this.menuService.save(menu);
        } catch (Exception e) {
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value = "update")
    @ResponseBody
    public Result<Menu> update(Menu menu) {
             
        Result<Menu> result = new Result<>();
        try {
            this.menuService.update(menu);
        } catch (Exception e) {
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
    
    @RequestMapping(value={"delete/{id}"})
    @ResponseBody
    public Result<Menu> update(@PathVariable("id") Long id) {
        Result<Menu> result = new Result<>();
        try {
            this.menuService.delete(id);
        } catch (BusinessException e) {
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("MenuController:{}", e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
}
