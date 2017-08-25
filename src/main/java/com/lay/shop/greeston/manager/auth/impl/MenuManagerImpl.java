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
package com.lay.shop.greeston.manager.auth.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.greeston.command.auth.MenuCommand;
import com.lay.shop.greeston.dao.auth.MenuDao;
import com.lay.shop.greeston.manager.auth.MenuManager;
@Service("menuManager")
public class MenuManagerImpl implements MenuManager {

    @Autowired
    private MenuDao menuDao;
    
    @Override
    public List<MenuCommand> findLeftMenuItems(Long userId, Long ouId) {
        Set<MenuCommand> root = new LinkedHashSet<MenuCommand>();
        List<MenuCommand> allMenu = menuDao.findAllMenuCommandList();
        
        // 获取所有的子节点
        List<MenuCommand> childMenu = menuDao.findLeftMenuItems(userId,ouId);     
        //组合菜单
        getMenusJoin(childMenu , root,allMenu);        
        List<MenuCommand> rootMenu =new ArrayList<MenuCommand>();
        rootMenu.addAll(root);
        return rootMenu;
    }

    /**
     * 组合菜单
     * @param childMenu
     */
    public void getMenusJoin(List<MenuCommand> childMenu ,Set<MenuCommand> root,List<MenuCommand> allMenu) {
        Set<Long> parentIdSet=new HashSet<Long>();//找出所有菜单父节点的id
        for(MenuCommand mic:childMenu){
            if(mic.getParentId()!=null){
                parentIdSet.add(mic.getParentId());
            }else{
                root.add(mic);
            }
        }
        if(!parentIdSet.isEmpty()){
            List<MenuCommand> parent = new ArrayList<MenuCommand>();//获取父菜单对象
            for(Long id:parentIdSet){
                MenuCommand pmic=findCommandById(id,allMenu);
                if(pmic!=null){
                    parent.add(pmic);
                }
            }
            relevance(parent,childMenu);//将子节点添加到对应的父节点
            getMenusJoin(parent,root,allMenu);//递归查找父菜单
        }
        
    }

    /**
     * 将子节点添加到对应的父节点
     * @param parent
     * @param child
     */
    public void relevance(List<MenuCommand> parent,List<MenuCommand> child) {
        for (MenuCommand rmic : parent) {
            List<MenuCommand> childList = new ArrayList<MenuCommand>();
    
            for (MenuCommand cmic : child) {
                if (rmic.getId() == cmic.getParentId()) {
                    childList.add(cmic);
                }
            }
    
            if (!childList.isEmpty()) {
                if(rmic.getChildList()!=null && !rmic.getChildList().isEmpty()){
                    rmic.getChildList().addAll(childList);
                }else{
                    rmic.setChildList(childList);
                }
            }
        }
        
        if (parent != null) {// 去除没有子节点的菜单

            int size = parent.size();
            for (int i = 0; i < size; i++) {
                MenuCommand rmic = parent.get(i);
                if (rmic.getChildList() == null) {
                    parent.remove(i);
                    i--;
                    size--;
                }
            }
        }
    }
    
    /**
     * 根据ID获取数据
     * @param id
     * @param allMenu
     * @return
     */
    private MenuCommand findCommandById(Long id,List<MenuCommand> allMenu) {
        for (MenuCommand menu : allMenu) {
            if(id==menu.getId()){
                return menu;
            }
        }
        return null;
    }

    
    /**
     * 查询所有的菜单
     * @author Lay
     * @date 2017年8月25日 下午4:54:19
     * @return
     * @see com.lay.shop.greeston.manager.auth.MenuManager#findAllMenuItems()
     * @since
     */
    @Override
    public List<MenuCommand> findAllMenuItems() {

        Set<MenuCommand> root = new LinkedHashSet<MenuCommand>();
        List<MenuCommand> allMenu = menuDao.findAllMenuCommandList(); 
        //组合菜单
        getMenusJoin(allMenu , root,allMenu);        
        List<MenuCommand> rootMenu =new ArrayList<MenuCommand>();
        rootMenu.addAll(root);
        return rootMenu;
    }
}
