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
import com.lay.shop.greeston.command.auth.PrivilegeCommand;
import com.lay.shop.greeston.controller.BaseController;
import com.lay.shop.greeston.manager.auth.PrivilegeManager;
import com.lay.shop.greeston.model.auth.Privilege;

@Controller
@RequestMapping(value = "/auth")
public class PrivilegeController extends BaseController {
    
    @Autowired
    private PrivilegeManager privilegeManager;

    @RequestMapping(value = {"/pri/list"})
    public String list(@QueryBeanParam QueryBean queryBean, Model model){
        Page page = queryBean.getPage();
        Sort[] sorts = queryBean.getSorts();
        Map<String, Object> param = queryBean.getParaMap();
        
        Pagination<PrivilegeCommand> pagination = this.privilegeManager.findPrivilegeListWithPage(page, sorts, param);
        model.addAttribute("pagination",pagination);
        return "modules/auth/privilege/list";
    }
    
    @RequestMapping(value = {"/pri/prifun"})
    @ResponseBody
    public PrivilegeCommand priFun(Long id) {
        PrivilegeCommand command = this.privilegeManager.findAllPrifunUrlByAclId(id);
        return command;
    }
    
    @RequestMapping(value = {"/pri/add"})
    @ResponseBody
    public Result<Object> add(PrivilegeCommand command, Model model){
        Result<Object> result = new Result<>();
        try{
            privilegeManager.saveOrUpdatePrivilege(command);  
        }catch(BusinessException e){
            
        }catch(Exception e){
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }        
        return result;
    }
    
    @RequestMapping(value = {"/pri/get"})
    @ResponseBody
    public PrivilegeCommand get(Long id) {        
        return this.privilegeManager.findAclAndUrlById(id);
    }
    
    @RequestMapping(value = {"/pri/del"})
    public String deleteById(Long id) {
        privilegeManager.deletePrivilegeById(id);
        return "redirect:/auth/pri/list";
    }
    
    @RequestMapping(value = {"/pri/allAcl"})
    @ResponseBody
    public Result<List<Privilege>> findAllAcl(Long ouTypeId){
        Result<List<Privilege>> result = new Result<>();
        try {
            Privilege p = new Privilege();
            p.setOuTypeId(ouTypeId);
            result.setData(this.privilegeManager.findAllPri(p));
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }
        return result;
    }
}
