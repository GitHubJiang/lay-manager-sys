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
package com.lay.shop.greeston.controller.auth;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.web.Result;
import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.common.web.controller.BaseController;
import com.lay.shop.greeston.command.auth.RoleCommand;
import com.lay.shop.greeston.manager.auth.PrivilegeManager;
import com.lay.shop.greeston.manager.auth.RoleManager;
import com.lay.shop.greeston.model.auth.Privilege;
import com.lay.shop.greeston.model.auth.Role;

@Controller
@RequestMapping("/auth/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private PrivilegeManager privilegeManager;
    
    @RequestMapping(value = {"/list"})
    public String list(@QueryBeanParam QueryBean queryBean, Model model) {
        Page page = queryBean.getPage();
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> param = queryBean.getParaMap();
        Pagination<RoleCommand> pagination = this.roleManager.findRoleListWithPage(page, sorts, param);
        model.addAttribute("pagination", pagination);
        return "modules/auth/role/list";
    }
    
    @RequestMapping(value = {"/allPri"})
    @ResponseBody
    public List<Privilege> allPri() {
        return this.privilegeManager.findAllPri(null);
    }
    
    @RequestMapping(value = {"/add"})
    @ResponseBody
    public Result<Object> add(RoleCommand command, Model model){
        Result<Object> result = new Result<>();
        try{
            roleManager.saveOrUpdateRole(command);  
        }catch(BusinessException e){
            
        }catch(Exception e){
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.FAILED.getValue());
            result.setMsg(ErrorCodes.FAILED.getMsg());
        }        
        return result;
    }
    
    @RequestMapping(value = {"/get"})
    @ResponseBody
    public RoleCommand get(Long id) {        
        return this.roleManager.findRoleAndPriById(id);
    }
    
    @RequestMapping(value = {"/del"})
    public String delete(Long id) {
        this.roleManager.deleteRoleById(id);
        return "redirect:/auth/role/list";
    }
    
    /**获取角色列表*/
    @RequestMapping(value = {"/rolelist"})
    @ResponseBody
    public Result<List<Role>> getRole(Long ouTypeId) {
        Result<List<Role>> result = new Result<>();
        try {
            Role role = new Role();
            role.setOuTypeId(ouTypeId);
            List<Role> data = this.roleManager.findRoleListByParam(role);
            result.setData(data);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.FAILED.getValue());
            result.setMsg(ErrorCodes.FAILED.getMsg());
        }
        return result;
    }
    
    /*@RequestMapping(value = {"/get"})
    @ResponseBody
    public RoleCommand get(Long id) {        
        return this.roleManager.findRoleAndPriById(id);
    }*/
}
