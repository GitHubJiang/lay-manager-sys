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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.greeston.controller.BaseController;
import com.lay.shop.greeston.manager.auth.OperationUnitTypeManager;
import com.lay.shop.greeston.model.auth.OperationUnitType;
@Controller
@RequestMapping(value = "/auth")
public class OperationUnitTypeController extends BaseController {

    @Autowired
    private OperationUnitTypeManager operationUnitTypeManager;
    
    //权限管理使用
    @RequestMapping(value = {"/opt/allopt"})
    @ResponseBody
    public List<OperationUnitType> allopt() {        
        return this.operationUnitTypeManager.findAllOptByParam(null);
    }
}
