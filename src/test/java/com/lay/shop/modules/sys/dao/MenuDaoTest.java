package com.lay.shop.modules.sys.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lay.shop.SpringTest;
import com.lay.shop.modules.sys.command.MenuCommand;

public class MenuDaoTest extends SpringTest{
    
    @Autowired
    private MenuDao menuDao;
    @Test
    public void findUserInfoByLoginNameTest(){
        List<MenuCommand> menu = this.menuDao.findIndexMenu(1, null, "LayJiang");
        this.printPrettyJson(JSON.toJSONString(menu));
        ;
    }
    
}
