package com.lay.shop.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.dao.MenuDao;
import com.lay.shop.modules.sys.service.MenuService;
import com.lay.shop.modules.sys.utils.TreeUtils;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 下午2:33:25
 * @since
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;
   
    @SuppressWarnings("unchecked")
    @Override    
    public List<MenuCommand> findIndexMenu(Integer type,Long id,String loginName){
        
        List<MenuCommand> list = this.menuDao.findIndexMenu(loginName);
        
        List<MenuCommand> menuList = new ArrayList<>();
        if(type.equals(1)){            
            for(MenuCommand menu : list){
                if(menu.getType().equals(1)){
                    menuList.add(menu);
                }
            }
        }else if(type.equals(2)){
            MenuCommand menuCommand = (MenuCommand)TreeUtils.getInstance().toTreeNode(list, 0l);
            for(MenuCommand menu : (List<MenuCommand>)menuCommand.getChildren().getList()){
                if(menu.getParentId().equals(id)){
                    menuList.add(menu);
                }
            }
        }
        return menuList;
    }
}
