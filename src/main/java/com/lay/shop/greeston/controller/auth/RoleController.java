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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.web.bind.QueryBean;
import com.lay.shop.common.web.bind.QueryBeanParam;
import com.lay.shop.greeston.command.auth.RoleCommand;
import com.lay.shop.greeston.controller.BaseController;
import com.lay.shop.greeston.manager.auth.PrivilegeManager;
import com.lay.shop.greeston.manager.auth.RoleManager;

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
        return "modules/auth/roleList";
    }
}
