package com.lay.shop.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.lay.shop.modules.sys.command.MenuCommand;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 下午2:33:25
 * @since
 */
public interface MenuService{
    
    List<<T extends MenuCommand>> findIndexMenu(Integer type,Long id);
    
}
