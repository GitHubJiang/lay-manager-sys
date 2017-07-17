package com.lay.shop.modules.sys.service;

import java.util.List;

import com.lay.shop.modules.sys.command.MenuCommand;
import com.lay.shop.modules.sys.model.Menu;

public interface MenuService {
    
    /**生成功能菜单*/
    List<MenuCommand> findMainMenu(Long userId,String orgCode);
    /**生成侧边菜单*/
    List<MenuCommand> findLeftMenu(Long userId,String orgCode,Long pid);    
    /**新增菜单*/
    void save(Menu menu);
    /**修改菜单*/
    void update(Menu menu);
    /**删除菜单*/
    void delete(Long id);
}
