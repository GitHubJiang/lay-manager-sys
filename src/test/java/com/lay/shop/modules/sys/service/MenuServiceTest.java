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
package com.lay.shop.modules.sys.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lay.shop.SpringTest;
import com.lay.shop.modules.sys.command.MenuCommand;

public class MenuServiceTest extends SpringTest{
   
    @Autowired
    private MenuService menuService;
    
    @Test
    public void findMainMenuTest(){
        List<MenuCommand> list = menuService.findMainMenu(1L, "ORG_SYSTEM");
        this.printPrettyJson(list);
    }
    
    @Test
    public void findLeftMenuTest(){
        List<MenuCommand> list = menuService.findLeftMenu(1L, "ORG_SYSTEM",2L);
       
        this.printPrettyJson(list);
    }
}
