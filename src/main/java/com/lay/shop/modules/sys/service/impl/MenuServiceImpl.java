package com.lay.shop.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lay.shop.modules.sys.dao.MenuDao;
import com.lay.shop.modules.sys.entity.Menu;
import com.lay.shop.modules.sys.service.MenuService;

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
    public List<Map<String,Object>> findIndexMenu(Integer type,Long id){
        Menu menu = new Menu();
        menu.setType(type);
        menu.setParentId(id);
        List<Menu> list = menuDao.findListByParam(menu);
        if(list){
            
        }
        return null;
    }
}
