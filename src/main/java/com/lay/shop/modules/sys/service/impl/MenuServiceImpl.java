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
package com.lay.shop.modules.sys.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lay.shop.common.constants.SystemConstants;
import com.lay.shop.common.utils.TreeUtils;
import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.dao.MenuDao;
import com.lay.shop.modules.sys.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    
    @Override
    public List<MenuCommand> findMainMenu(Long userId,String orgCode) {
        List<MenuCommand> mainMenu = Lists.newArrayList();
        List<MenuCommand> list = menuDao.findAllMenuList(userId, orgCode);
        List<MenuCommand> allList =  Lists.newArrayList();
        allList.addAll(list);
        this.generateMenu(list, allList);
        for(MenuCommand command :allList){
            if (command != null && SystemConstants.menuRoot.equals(command.getParentId())) {
                mainMenu.add(command);
            }            
        }
        return mainMenu;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MenuCommand> findLeftMenu(Long userId,String orgCode,Long pid) {
        List<MenuCommand> leftMenu = Lists.newArrayList();
        List<MenuCommand> list = menuDao.findAllMenuList(userId, orgCode);
        List<MenuCommand> allList =  Lists.newArrayList();
        allList.addAll(list);
        this.generateMenu(list, allList);
        MenuCommand menuCommand = (MenuCommand)TreeUtils.getInstance().menu(allList);        
        for(MenuCommand command : menuCommand.getChildren()){
            if(command.getId().equals(pid)){
                leftMenu = command.getChildren();
            }
        }
        return leftMenu;
    }

    
    private List<MenuCommand> generateMenu(List<MenuCommand> list, List<MenuCommand> allList){
        List<Long> ids = Lists.newArrayList();
        for(MenuCommand command : list){  
            if(command.getParentId()!=null){
                ids.add(command.getParentId());   
            }
        }
        if(ids.isEmpty()){
            return allList;
        }
        List<MenuCommand> parents = menuDao.findUpMenuList(ids);
        allList.addAll(parents);
        generateMenu(parents,allList);
        return allList;
    }
}

    
