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
   
    @Override    
    public List<MenuCommand> findIndexMenu(Integer type,Long id,String loginName){
        
        List<MenuCommand> list = this.menuDao.findIndexMenu(loginName);
        
        List<MenuCommand> menuList = new ArrayList<>();
        if("1".equals(type)){            
            for(MenuCommand menu : list){
                if("1".equals(menu.getType())){
                    menuList.add(menu);
                }
            }
        }else if("2".equals(type)){
            MenuCommand menuCommand = (MenuCommand)TreeUtils.getInstance().toTreeNode(menuList, 0l);
            menuList = menuCommand.getChildren().getList();
            for(MenuCommand menu : (List<MenuCommand>)menuCommand.getChildren().getList()){
                if(menu.getParentId().equals(id)){
                    menuList.add(menu);
                }
            }
        }
        return menuList;
    }
}
