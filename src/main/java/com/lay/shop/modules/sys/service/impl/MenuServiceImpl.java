package com.lay.shop.modules.sys.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lay.shop.common.constants.SystemConstants;
import com.lay.shop.common.utils.TreeUtils;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.dao.MenuDao;
import com.lay.shop.modules.sys.model.Menu;
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

    
    private List<MenuCommand> generateMenu(List<MenuCommand> list, List<MenuCommand> allList) {
        List<Long> ids = Lists.newArrayList();
        for (MenuCommand command : list) {
            if (!Validator.isNullOrEmpty(command.getParentId())&& !ids.contains(command.getParentId())) {
                ids.add(command.getParentId());
            }
        }
        if (ids.isEmpty()) {
            return allList;
        }
        List<MenuCommand> parents = menuDao.findUpMenuList(ids);
        allList.addAll(parents);
        generateMenu(parents, allList);
        return allList;
    }

    @Override
    public void save(Menu menu) {
        this.menuDao.insert(menu);
    }

    @Override
    public void update(Menu menu) {
        this.menuDao.update(menu);
    }

    @Override
    public void delete(Long id) {
        this.menuDao.delete(id);
    }
}

    
