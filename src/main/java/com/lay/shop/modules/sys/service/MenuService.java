package com.lay.shop.modules.sys.service;

import java.util.List;

import com.lay.shop.modules.sys.command.MenuCommand;

public interface MenuService {
    
    public List<MenuCommand> findMainMenu(Long userId,String orgCode);
    
    public List<MenuCommand> findLeftMenu(Long userId,String orgCode,Long pid);
    
}
